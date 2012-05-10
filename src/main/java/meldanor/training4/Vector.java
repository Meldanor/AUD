package meldanor.training4;

/**
 * Implementation of an array-based vector.
 * <p>
 * 
 * The implementation is inspired by <a
 * href="http://en.cppreference.com/w/cpp/container/vector"> of the <a
 * href="http://en.cppreference.com/w/cpp">C++ standard library</a>. (There is a
 * similar data structure <a
 * href="http://docs.oracle.com/javase/1.4.2/docs/api/java/util/Vector.html">
 * java.util.Vector</a> in the Java collections.)
 * <p>
 * 
 * Vectors can grow and shrink in size. Shrinking never frees memory. Growing
 * (see {@link #reserve}) over-allocates memory.
 * <p>
 * 
 * <em>Implementation notes:</em>
 * 
 * <ul>
 * <li>The utility method {@code check()} of the implementation ensures that an
 * index is valid or throws a {@code java.lang.ArrayIndexOutOfBoundsException}
 * otherwise.</li>
 * <li>We use an exponential growth as heuristic, i.e., there are (1.)
 * relatively few reallocations required, and (2.) we may waste a significant
 * amount of memory</li>
 * <li> {@link #reserve} allocates at least {@code MIN_SIZE}. This prevents
 * unnecessary reallocations for small vectors.</li>
 * </ul>
 */
//@<vector:class
public class Vector<T> implements Iterable<T> {

    static final int MIN_SIZE = 16;

    T[] data_ = null;
    int size_ = 0;
    // @>vector:class

    /**
     * create empty vector
     */
    // @<vector:ctor
    public Vector() {
    }
    // @>vector:ctor

    /** get number of entries [O(1)] */
    // @<vector:size
    public int size() {
        return size_;
    }
    // @>vector:size

    /**
     * Get Capacity, i.e., the maximum size the vector can grow without
     * allocating new storage.
     */
    // @<vector:capacity
    public int capacity() {
        return data_ != null ? data_.length : 0;
    }
    // @>vector:capacity

    /** Is vector empty? [O(1)] */
    public boolean empty() {
        return size() == 0;
    }

    /**
     * get first entry [O(1)]
     * 
     * @throws ArrayIndexOutOfBoundsException
     */
    public T front() {
        return at(0);
    }
    /**
     * get last entry [O(1)]
     * 
     * @throws ArrayIndexOutOfBoundsException
     */
    public T back() {
        return at(size() - 1);
    }

    /**
     * get i-th entry [O(1)]
     * 
     * @throws ArrayIndexOutOfBoundsException
     */
    // @<vector:at
    public T at(int i) {
        return data_[check(i)];
    }
    // @>vector:at

    /**
     * check and return index i or throw
     * 
     * @throws ArrayIndexOutOfBoundsException
     * @return i (or throw if i is not a valid index)
     */
    // @<vector:check
    int check(int i) {
        if (!(0 <= i && i < size_))
            throw new ArrayIndexOutOfBoundsException(i);
        return i;
    }
    // @>vector:check

    /**
     * Ensure {@link #capacity} for n entries. This method may allocates new
     * storage and copy data in O(n). New entries equal {@code null}.
     */
    @SuppressWarnings("unchecked")
    // @<vector:reserve
    public void reserve(int n) {
        n = (n >= MIN_SIZE) ? n : MIN_SIZE;
        if (capacity() < n) {
            T[] a = (T[]) new Object[n]; // unchecked
            for (int i = 0; i < size_; ++i)
                a[i] = data_[i];
            for (int i = size_; i < a.length; ++i)
                a[i] = null;
            data_ = a;
        }
    }
    // @>vector:reserve

    /**
     * Set {@link #size} of vector. Shrinks or grows the vector. On Growing, any
     * "new" entries equal {@code null}.
     */
    // @<vector:resize
    public void resize(int n) {
        if (n > capacity()) {
            int c = (3 * capacity()) / 2; // grow $\times\,\frac{3}{2}$
            reserve(c > n ? c : n);
        } else if (n > size_) {
            for (int i = size_; i < n; ++i)
                data_[i] = null; // garbage!
        }
        size_ = n;
    }
    // @>vector:resize

    /**
     * insert new entry obj at the end [O(1) for sufficient {@link #capacity}]
     */
    // @<vector:push_back
    public void push_back(T obj) {
        int n = size_;
        resize(n + 1); // usually not much to do!
        data_[n] = obj;
    }
    // @>vector:push_back

    /**
     * insert new entry obj at position i [O(n)]
     */
    // @<vector:insert
    public void insert(int i, T obj) {
        int n = size_;
        resize(n + 1);
        for (int j = n; j > i; --j)
            // shift right
            data_[j] = data_[j - 1];
        data_[i] = obj;
    }
    // @>vector:insert

    /**
     * remove last enytry [O(1)]
     * 
     * @throws ArrayIndexOutOfBoundsException
     */
    // @<vector:pop_back
    public void pop_back() {
        check(0);
        data_[--size_] = null; // garbage!
    }
    // @>vector:pop_back

    /**
     * remove entry at position i [O(n)]
     * 
     * @throws ArrayIndexOutOfBoundsException
     */
    // @<vector:erase
    public void erase(int i) {
        check(i);
        for (int j = i + 1; j < size_; ++j)
            // shift left
            data_[j - 1] = data_[j];
        data_[--size_] = null; // garbage!
    }
    // @>vector:erase

    /**
     * remove all entries (capacity remains) [O(1)]
     */
    public void clear() {
        for (int i = 0; i < size_; ++i)
            data_[i] = null; // garbage!
        size_ = 0;
    }

    //
    // iterators
    //

    /** Forward iterator */
    public class Iterator implements java.util.Iterator<T> {

        Vector<T> v_;
        int idx_;
        Iterator(Vector<T> v, int idx) {
            v_ = v;
            idx_ = idx;
        }

        /** return {@code true} unless "advanced" over tail */
        @Override
        public boolean hasNext() {
            return idx_ < v_.size();
        }

        /** return <em>current</em> entry and advance */
        @Override
        public T next() {
            return v_.at(idx_++);
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
    }

    /** get forward iterator */
    @Override
    public Iterator iterator() {
        return new Iterator(this, 0);
    }

    @Override
    public String toString() {
        String rv = "[";
        for (int i = 0; i < size(); ++i) {
            rv += data_[i];
            if (i < size() - 1)
                rv += ",";
        }
        rv += "]";
        return rv;
    }
}
