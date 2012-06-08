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
import java.util.PriorityQueue;

public class MinHeap<T extends Comparable<T>> {

    private ArrayList<T> heap;

    public MinHeap() {
        heap = new ArrayList<T>(16);
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

    public void upHeap(int k) {

        T e = heap.get(k);
        while (k > 0 && isLess(e, heap.get((k - 1) / 2))) {
            heap.set(k, heap.get((k - 1) / 2));
            k = (k - 1) / 2;
        }

        heap.set(k, e);
    }

    private boolean isLess(T e1, T e2) {
        return e1.compareTo(e2) < 0;
    }

    public void downHeap(int k) {
        T e = heap.get(k);
        while (k <= heap.size()) {
            int j = (2 * k) + 1;
            if (heap.get(j).compareTo(heap.get(j + 1)) > 0)
                ++j;
            if (e.compareTo(heap.get(j)) <= 0)
                break;

            heap.set(k, heap.get(j));
            k = j;
        }

        heap.set(k, e);
    }

    public void insert(T obj) {
        heap.add(obj);
        upHeap(heap.size() - 1);
    }

    public String toString() {
        // do not change because of backend-control
        return heap.toString();
    }

    public static void main(String[] args) {

        MinHeap<Character> heap = new MinHeap<Character>();
        Character[] chars = {'X', 'T', 'O', 'G', 'S', 'M', 'N', 'A', 'E', 'R', 'A', 'I'};
        for (Character c : chars)
            heap.insert(c);

        System.out.println(heap);
        System.out.println();
        PriorityQueue<Character> queue2 = new PriorityQueue<Character>();
        for (Character c : chars)
            queue2.add(c);

        System.out.println(queue2);
    }
}
