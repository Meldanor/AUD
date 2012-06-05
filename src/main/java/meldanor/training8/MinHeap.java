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

public class MinHeap<T extends Comparable<T>> {

    private ArrayList<T> heap;

    private int counter;

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
        T temp = heap.get(k / 2);
        while (k > 0 && e.compareTo(temp) > 1) {
            heap.set(k, temp);
            k /= 2;
            temp = heap.get(k);
        }

        heap.set(k, e);
    }

    public void downHeap(int k) {
        // TODO: implementation
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
        heap.insert('X'); 
        System.out.println(heap);
        
        heap.insert('T'); 
        System.out.println(heap);
        
        heap.insert('O'); 
        System.out.println(heap);
        
        heap.insert('G'); 
        System.out.println(heap);
        
        heap.insert('S'); 
        System.out.println(heap);
        
        heap.insert('M'); 
        System.out.println(heap);
        
        heap.insert('X'); 
        System.out.println(heap);
        
        heap.insert('X'); 
        System.out.println(heap);
        
        heap.insert('X'); 
        System.out.println(heap);
    }
}
