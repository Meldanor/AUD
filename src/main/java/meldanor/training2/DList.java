package meldanor.training2;

import java.util.NoSuchElementException;

/**
 * Implementation of a doubly linked list.
 * <p>
 * 
 * The implementation is inspired by <a
 * href="http://en.cppreference.com/w/cpp/container/list"> std::forward_list</a>
 * of the <a href="http://en.cppreference.com/w/cpp">C++ standard library</a>.
 * <p>
 * 
 * DList does <em>not</em> define a bidirectional {@code ListIterator}.
 * 
 * DList defines {@link ForwardIterator} (standard) {@link #iterator} and
 * {@link BackwardIterator} {@link #reverse_iterator}.
 * <ul>
 * 
 * <li>Iterators remain valid after insertion.</li>
 * 
 * <li>Iterators remain valid after removing entries, <em>unless</em> the
 * current entry (that an iterator "points to") is removed.
 * <li>
 * 
 * <li>There are methods to insert entries before and after an interator.</li>
 * 
 * </ul>
 * <p>
 * 
 * <em>Implementation note:</em> Methods that access (or erase) entries
 * generally assume that entries exist (!{@link #empty}). A
 * java.lang.IndexOutOfBoundsException is thrown otherwise (see utility method
 * {@code check()}).
 */
//@<dlist:class
public class DList<T> implements Iterable<T>, Graphvizable {
    class Node {
        T data_ = null;
        Node next_ = null;
        Node prev_ = null;

        public Node(T obj, Node prv, Node nxt) {
            data_ = obj;
            prev_ = prv;
            next_ = nxt;
        }
    }

    Node head_ = null;
    Node tail_ = null;
    // @>dlist:class
    /**
     * create empty list
     */
    // @<dlist:ctor
    public DList() {
    }
    // @>dlist:ctor

    /**
     * determine number of entries [O(n)]
     */
    // @<dlist:size
    public int size() {
        int n = 0;
        Node node = head_;
        while (node != null) {
            node = node.next_;
            ++n;
        }
        return n;
    }
    // @>dlist:size

    /**
     * determine if list is empty [O(1)]
     */
    public boolean empty() {
        assert ((head_ == null && tail_ == null) || (head_ != null && tail_ != null));
        return head_ == null;
    }

    /**
     * helper: check for null and return node
     * 
     * @throws IndexOutOfBoundsException
     * @return node (if {@code node!=null}, throws otherwise)
     */
    // @<dlist:check
    Node check(Node node) {
        if (node == null)
            throw new IndexOutOfBoundsException();
        return node;
    }
    // @>dlist:check

    /**
     * retrieve first entry (must exist) [O(1)]
     */
    // @<dlist:front
    public T front() {
        return check(head_).data_;
    }
    // @>dlist:front

    /**
     * retrieve last entry (must exist) [O(1)]
     */
    // @<dlist:back
    public T back() {
        return check(tail_).data_;
    }
    // @>dlist:back

    /**
     * retrieve i-th entry [O(n)]
     * 
     * @throws IndexOutOfBoundsException
     */
    // @<dlist:at
    public T at(int i) {
        Node node = head_;
        for (int j = 0; node != null && j < i; ++j)
            node = node.next_;
        return check(node).data_;
    }
    // @>dlist:at

    /**
     * insert entry at front of list [O(1)]
     */
    // @<dlist:push_front
    public void push_front(T obj) {
        head_ = new Node(obj, null, head_);
        if (head_.next_ != null)
            head_.next_.prev_ = head_;
        if (tail_ == null)
            tail_ = head_;
    }
    // @>dlist:push_front

    /**
     * append entry obj at end of list [O(n)]
     */
    // @<dlist:push_back
    public void push_back(T obj) {
        tail_ = new Node(obj, tail_, null);
        if (tail_.prev_ != null)
            tail_.prev_.next_ = tail_;
        if (head_ == null)
            head_ = tail_;
    }
    // @>dlist:push_back

    /**
     * insert new entry obj after node
     */
    // @<dlist:insert_after
    void insert_after(Node node, T obj) {
        Node prv, cur, nxt;
        if (node == null)
            push_front(obj);
        else if (node == tail_)
            push_back(obj);
        else {
            prv = node; // !=null !
            nxt = node.next_; // !=null !
            cur = new Node(obj, prv, nxt);
            prv.next_ = cur;
            nxt.prev_ = cur;
        }
    }
    // @>dlist:insert_after

    /**
     * insert new entry obj at position i [O(n)]
     */
    // @<dlist:insert
    public void insert(int i, T obj) {
        if (i == 0)
            insert_after((Node) null, obj);
        else {
            Node node = head_;
            for (int j = 0; j < i - 1 && node != null; ++j)
                node = node.next_;
            insert_after(check(node), obj);
        }
    }
    // @>dlist:insert

    /**
     * erase first entry (must exist) [O(1)]
     */
    // @<dlist:pop_front
    public void pop_front() {
        head_ = check(head_).next_;
        if (head_ == null)
            tail_ = null;
        else
            head_.prev_ = null;
    }
    // @>dlist:pop_front

    /**
     * erase last entry (must exist) [O(n)]
     */
    // @<dlist:pop_back
    public void pop_back() {
        tail_ = check(tail_).prev_;
        if (tail_ == null)
            head_ = null;
        else
            tail_.next_ = null;
    }
    // @>dlist:pop_back

    /**
     * erase entry at position i [O(n)]
     */
    // @<dlist:erase
    public void erase(int i) {
        Node node = head_;
        for (int j = 0; j < i && node != null; ++j)
            node = node.next_;
        check(node);

        if (node.prev_ != null)
            node.prev_.next_ = node.next_;
        else
            head_ = node.next_; // erased first!

        if (node.next_ != null)
            node.next_.prev_ = node.prev_;
        else
            tail_ = node.prev_; // erased last!
    }
    // @>dlist:erase

    /**
     * Erase all entries. [O(1)]
     */
    // @<dlist:clear
    public void clear() {
        head_ = tail_ = null;
    }
    // @>dlist:clear

    //
    // iterators
    //

    /** Forward iterator */
    public class ForwardIterator implements java.util.Iterator<T> {
        Node node_ = null;
        ForwardIterator(Node node) {
            node_ = node;
        }

        /** return {@code true} unless "advanced" over tail */
        @Override
        public boolean hasNext() {
            return node_ != null;
        }
        /** return <em>current</em> entry and advance forward */
        @Override
        public T next() {
            if (node_ == null)
                throw new NoSuchElementException();
            T data = node_.data_;
            node_ = node_.next_;
            return data;
        }
        /** return <em>current</em> entry and step backwards */
        public T previous() {
            if (node_ == null)

                throw new NoSuchElementException();
            T data = node_.data_;
            node_ = node_.prev_;
            return data;
        }
        /**
         * not implemented
         * 
         * @throws UnsupportedOperationException
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean equals(Object other) {
            return node_ == ((ForwardIterator) other).node_;
        }
    }

    /**
     * Backward iterator. Reverses semantics of {@link ForwardIterator#next} and
     * {@link ForwardIterator#previous}.
     */
    public class BackwardIterator extends ForwardIterator {
        BackwardIterator(Node node) {
            super(node);
        }
        @Override
        public T next() {
            return super.previous();
        }
        @Override
        public T previous() {
            return super.next();
        }
    }

    /** get forward iterator */
    @Override
    public ForwardIterator iterator() {
        return new ForwardIterator(head_);
    }
    /** get backward iterator iterator */
    public BackwardIterator reverse_iterator() {
        return new BackwardIterator(tail_);
    }

    /** insert entry <em>after</em> iterator position */
    public void insert_after(ForwardIterator i, T object) {
        check(i.node_);
        insert_after(i.node_, object);
    }
    /** insert entry <em>before</em> iterator position */
    public void insert_before(ForwardIterator i, T object) {
        check(i.node_);
        insert_after(i.node_.prev_, object);
    }

    //
    // output
    //

    @Override
    public String toString() {
        String rv = "[";
        Node node = head_;
        while (node != null) {
            rv += node.data_.toString();
            if (node.next_ != null)
                rv += ",";
            node = node.next_;
        }
        rv += "]";
        return rv;
    }

    @Override
    public String toDot() {
        String rv = "digraph DList {\n\t\n\tnode [shape=box];\n\t";
        Node node = head_;
        int i = 0;
        while (node != null) {
            String prv = node.prev_ != null ? node.prev_.data_.toString() : "null";
            String nxt = node.next_ != null ? node.next_.data_.toString() : "null";

            rv += "\"" + node.data_.toString() + "\" -> \"" + prv + "\" [color=blue,label=prev];\n\t" + "\"" + node.data_.toString() + "\" -> \"" + nxt + "\" [color=blue,label=next];\n\t";

            node = node.next_;
            ++i;
        }
        rv += "\n}\n";
        return rv;
    }
}
