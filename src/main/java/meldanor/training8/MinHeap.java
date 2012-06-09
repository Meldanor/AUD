/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training8;

/**
 * @author Meldanor
 *
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MinHeap<T extends Comparable<T>> {

    private ArrayList<T> heap;

    /* COMPARATOR FOR MIN AND MAX HEAP */
    public static final Comparator MIN_HEAP = new Comparator() {
        public int compare(Object o1, Object o2) {
            return ((Comparable) o1).compareTo(o2);
        }
    };

    public static final Comparator MAX_HEAP = new Comparator() {
        public int compare(Object o1, Object o2) {
            return ((Comparable) o2).compareTo(o1);
        }
    };

    private static final int MIN_SIZE = 16;

    private Comparator<T> comparator;

    public MinHeap() {
        this(MIN_SIZE, MIN_HEAP);
    }

    public MinHeap(Comparator comparator) {
        this(MIN_SIZE, comparator);
    }

    public MinHeap(int initialSize) {
        this(initialSize, MIN_HEAP);
    }

    public MinHeap(int initialSize, Comparator comparator) {
        heap = new ArrayList<T>(initialSize);
        this.comparator = comparator;
    }

    public ArrayList<T> getHeap() {
        // do not change because of backend-control
        return heap;
    }

    public int getSize() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private boolean isLess(T e1, T e2) {
        return comparator.compare(e1, e2) < 0;
    }

    private boolean isHigher(T e1, T e2) {
        return comparator.compare(e1, e2) > 0;
    }

    public T poll() {

        T e = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        downHeap(0);
        return e;
    }

    public void downHeap(int k) {
        T e = heap.get(k);
        // FIRST HALF OF HEAP = ALL LEVEL BUT NOT THE LAST ROW
        // SECOND HALF OF HEAP = LAST ROW
        int half = heap.size() >> 1;
        while (k < half) {

            // CHILDINDEX = (K / 2) +1
            int childIndex = (k << 1) + 1;
            // CHECK IF RIGHT CHILD IS HIGHER
            if (childIndex + 1 < heap.size() && isHigher(heap.get(childIndex), heap.get(childIndex + 1)))
                ++childIndex;
            // IF CHILD IS NOT HIGHER STOP SINKING
            if (!isHigher(e, heap.get(childIndex)))
                break;

            // CHILD IS NOW PARRENT
            heap.set(k, heap.get(childIndex));
            // GO TO NEXT CHILD
            k = childIndex;
        }

        // SET ELEMENT AT FINAL POSITION
        heap.set(k, e);
    }

    public void insert(T obj) {
        heap.add(obj);
        upHeap(heap.size() - 1);
    }

    public void upHeap(int k) {

        T e = heap.get(k);
        while (k > 0 && isLess(e, heap.get((k - 1) >> 1))) {
            heap.set(k, heap.get((k - 1) >> 1));
            k = (k - 1) >> 1;
        }

        heap.set(k, e);
    }

    public String toString() {
        // do not change because of backend-control
        return heap.toString();
    }

    public static void main(String[] args) {

        MinHeap<Character> heap = new MinHeap<Character>(MIN_HEAP);
        Character[] chars = {'X', 'T', 'O', 'G', 'S', 'M', 'N', 'A', 'E', 'R', 'A', 'I'};
        for (Character c : chars)
            heap.insert(c);

        System.out.println(heap);
        heap.poll();
        System.out.println(heap);

        System.out.println();

        // CONTROLL UNIT - PRIORITYQUEUE IS AN IMPLEMENTATION OF A MIN HEAP
        PriorityQueue<Character> queue2 = new PriorityQueue<Character>();
        for (Character c : chars)
            queue2.add(c);

        System.out.println(queue2);
        queue2.poll();
        System.out.println(queue2);

        if (queue2.toString().equals(heap.toString())) {
            System.out.println("Heap is correct");
        }
    }
}
