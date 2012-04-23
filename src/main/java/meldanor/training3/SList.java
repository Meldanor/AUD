package meldanor.training3;

import java.util.NoSuchElementException;
import java.util.Vector;

import meldanor.training2.DList;
import meldanor.training2.Graphvizable;

/**
 * Implementation of a singly linked list.
 * <p>
 * 
 * The implementation is inspired by <a
 * href="http://en.cppreference.com/w/cpp/container/forward_list">
 * std::forward_list</a>c (formerly extension <a
 * href="http://www.sgi.com/tech/stl/Slist.html">std::slist</a>) of the <a
 * href="http://en.cppreference.com/w/cpp">C++ standard library</a>.
 * <p>
 * 
 * Note that we implement also operations like {@link #back} and
 * {@link #push_back} and {@link #pop_back}, which are O(n) and inefficient for
 * singly linked lists. <em>They should not be
 * implemented (or used) for a forward iterable container!</em> (They are
 * incldued only to contrast {@link Vector} and {@link DList}.)
 * <p>
 * 
 * SList defines a (forward) {@link Iterator}.
 * 
 * <ul>
 * 
 * <li>Iterators implement the java.util.Iterator interface. Note that
 * {@link Iterator#hasNext} checks whether the <em>current</em> entry is valid,
 * and {@link Iterator#next} returns the current entry whil advancing to the
 * subsequent entry.
 * <p>
 * <em>This is the standard
 * semantic, but I still find the terms confusing.</em>
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
//@<slist:class
public class SList<T> implements Iterable<T>, Graphvizable {
    class Node {
        T data_ = null;
        Node next_ = null;

        public Node(T data, Node next) {
            data_ = data;
            next_ = next;
        }
    }

    Node head_ = null;
    // @>slist:class
    /**
     * create empty list
     */
    // @<slist:ctor
    public SList() {
    }
    // @>slist:ctor

    /**
     * determine number of entries [O(n)]
     */
    // @<slist:size
    public int size() {
        int n = 0;
        Node node = head_;
        while (node != null) {
            node = node.next_;
            ++n;
        }
        return n;
    }
    // @>slist:size

    /**
     * determine if list is empty [O(1)]
     */
    public boolean empty() {
        return head_ == null;
    }

    /**
     * helper: check for null and return node
     * 
     * @throws IndexOutOfBoundsException
     * @return node (if {@code node!=null}, throws otherwise)
     */
    // @<slist:check
    Node check(Node node) {
        if (node == null)
            throw new IndexOutOfBoundsException();
        return node;
    }
    // @>slist:check

    /**
     * retrieve first entry (must exist) [O(1)]
     */
    // @<slist:front
    public T front() {
        return check(head_).data_;
    }
    // @>slist:front

    /**
     * retrieve last entry (must exist) [O(n)]
     */
    public T back() {
        check(head_);
        Node node = head_;
        while (node.next_ != null)
            node = node.next_;
        return node.data_;
    }

    /**
     * retrieve i-th entry [O(n)]
     */
    // @<slist:at
    public T at(int i) {
        Node node = head_;
        for (int j = 0; node != null && j < i; ++j)
            node = node.next_;
        return check(node).data_;
    }
    // @>slist:at

    /**
     * insert entry at front of list [O(1)]
     */
    // @<slist:push_front
    public void push_front(T obj) {
        head_ = new Node(obj, head_);
    }
    // @>slist:push_front

    /**
     * append entry obj at end of list [O(n)]
     */
    // @<slist:push_back
    public void push_back(T obj) {
        if (head_ == null) // special case!
            head_ = new Node(obj, null);
        else {
            Node node = head_;
            while (node.next_ != null)
                node = node.next_;
            node.next_ = new Node(obj, null);
        }
    }
    // @>slist:push_back

    /**
     * insert new entry obj after node
     */
    // @<slist:insert_after
    void insert_after(Node node, T obj) {
        if (node == null)
            head_ = new Node(obj, head_);
        else {
            Node nxt = node.next_;
            node.next_ = new Node(obj, nxt);
        }
    }
    // @>slist:insert_after

    /**
     * insert new entry obj at position i [O(n)]
     */
    // @<slist:insert
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
    // @>slist:insert

    /**
     * erase first entry (must exist) [O(1)]
     */
    // @<slist:pop_front
    public void pop_front() {
        head_ = check(head_).next_;
    }
    // @>slist:pop_front

    /**
     * erase last entry (must exist) [O(n)]
     */
    // @<slist:pop_back
    public void pop_back() {
        Node node = check(head_), prv = null;
        while (node.next_ != null) {
            prv = node;
            node = node.next_;
        }
        if (prv == null)
            head_ = null;
        else
            prv.next_ = null;
    }
    // @>slist:pop_back

    /**
     * erase entry at position i [O(n)]
     */
    // @<slist:erase
    public void erase(int i) {
        if (i == 0)
            head_ = check(head_).next_;
        else {
            Node node = head_;
            for (int j = 0; j < i - 1 && node != null; ++j)
                node = node.next_;
            check(node);
            check(node.next_);

            node.next_ = node.next_.next_;
        }
    }
    // @>slist:erase

    /**
     * Erase all entries. [O(1)]
     */
    // @<slist:clear
    public void clear() {
        head_ = null;
    }
    // @>slist:clear

    //
    // iterators
    //

    /** Forward iterator */
    // @<slist:iterator:class
    public class Iterator<E> implements java.util.Iterator<T> {

        Node node_ = null;
        Iterator(Node node) {
            node_ = node;
        }
        // @>slist:iterator:class

        /** return {@code true} unless "advanced" over tail */
        @Override
        // @<slist:iterator:hasnext
        public boolean hasNext() {
            return node_ != null;
        }
        // @>slist:iterator:hasnext

        /** return <em>current</em> entry and advance */
        @Override
        // @<slist:iterator:next
        public T next() {
            if (node_ == null)
                throw new NoSuchElementException();
            T data = node_.data_;
            node_ = node_.next_;
            return data;
        }
        // @>slist:iterator:next
        /**
         * not implemented
         * 
         * @throws UnsupportedOperationException
         */
        @Override
        // @<slist:iterator:remove
        public void remove() {
            throw new UnsupportedOperationException();
        }
        // @>slist:iterator:remove
        @Override
        public boolean equals(Object other) {
            return node_ == ((Iterator) other).node_;
        }
    }

    /** insert entry <em>after</em> iterator position */
    public void insert_after(Iterator<T> i, T object) {
        check(i.node_);
        insert_after(i.node_, object);
    }

    /** get forward iterator */
    @Override
    // @<slist:iterator
    public Iterator<T> iterator() {
        return new Iterator<T>(head_);
    }
    // @>slist:iterator

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
        String rv = "digraph SList {\n\t\n\tnode [shape=box];\n\t";
        Node node = head_;
        int i = 0;
        while (node != null) {
            String nxt = node.next_ != null ? node.next_.data_.toString() : "null";
            rv += "\"" + node.data_.toString() + "\"";
            rv += " -> \"" + nxt + "\" [color=blue,label=next];\n\t";
            node = node.next_;
            ++i;
        }
        rv += "\n}\n";
        return rv;
    }
}
