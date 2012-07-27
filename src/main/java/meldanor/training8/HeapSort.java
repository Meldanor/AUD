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

import java.util.ArrayList;
import java.util.Comparator;

/**
 * <pre>
 * Schreiben Sie eine Klasse MinHeap<T extends Comparable<T>> mit einer geeigneten Datenstruktur (java.util.ArrayList) und den Methoden
 * 
 *     public ArrayList<T> getHeap(): liefert den internen Heap zurück,
 *     public int getSize(): gibt die Anzahl der Einträge an,
 *     public boolean isEmpty(): gibt an, ob der Heap leer ist,
 *     public void downHeap(int k): stellt die Heap-Eigenschaft durch ein "Versickern" des Elementes im Knoten k her,
 *     public void insert(T obj): fügt ein Element in den Heap ein.
 * 
 * Testen Sie diese Methode, indem Sie aus den Buchstaben
 * 
 * X, T, O, G, S, M, N, A, E, R, A und I
 * 
 * einen MinHeap erzeugen.
 * </pre>
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class HeapSort<T extends Comparable<T>> {

    private ArrayList<T> heap;

    private int size = 0; // size of the real Heap

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

    public HeapSort() {
        this(MIN_SIZE, MAX_HEAP);
    }

    public HeapSort(Comparator comparator) {
        this(MIN_SIZE, comparator);
    }

    public HeapSort(int initialSize) {
        this(initialSize, MAX_HEAP);
    }

    public HeapSort(int initialSize, Comparator comparator) {
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

    public void insert(T obj) {
        heap.add(obj);
        upHeap(heap.size() - 1);
        ++size;
    }

    public void upHeap(int k) {

        T e = heap.get(k);
        while (k > 0 && isLess(e, heap.get((k - 1) >> 1))) {
            heap.set(k, heap.get((k - 1) >> 1));
            k = (k - 1) >> 1;
        }

        heap.set(k, e);
    }

    public T remove() {
        T e = heap.get(0);
        heap.set(0, heap.get(size - 1));
        --size;
        downHeapSpecial(0);
        heap.set(size, e);
        return e;
    }

    public void downHeapSpecial(int k) {
        T e = heap.get(k);
        // FIRST HALF OF HEAP = ALL LEVEL BUT NOT THE LAST ROW
        // SECOND HALF OF HEAP = LAST ROW
        int half = size >> 1;
        while (k < half) {

            // CHILDINDEX = (K / 2) +1
            int childIndex = (k << 1) + 1;
            // CHECK IF RIGHT CHILD IS HIGHER
            if (childIndex + 1 < size && isHigher(heap.get(childIndex), heap.get(childIndex + 1)))
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

    public void heapSort() {
        while (size > 1)
            remove();

    }
    public String toString() {
        // do not change because of backend-control
        return heap.toString();
    }

    public static void main(String[] args) {
        int[] test = {8, 3, 7, 1, 5, 9, 6};
        HeapSort<Integer> heap = new HeapSort<Integer>();

        for (int i : test)
            heap.insert(i);

        // [9, 5, 8, 1, 3, 7, 6]
        System.out.println(heap);

        heap.heapSort();

        // [1, 3, 5, 6, 7, 8, 9]
        System.out.println(heap);

        HeapSort<Integer> heap2 = new HeapSort<Integer>();
        heap2.insert(8);
        heap2.insert(6);
        heap2.insert(7);
        heap2.insert(5);
        heap2.insert(3);
        heap2.insert(1);

        System.out.println(heap2);

        heap2.remove();
        heap2.remove();
        System.out.println(heap2);

    }
}
