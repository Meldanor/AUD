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
public class HeapSort<T extends Comparable<T>> {

    private ArrayList<T> heap;

    private int size = 0; // size of the real Heap

    public HeapSort() {
        // TODO: implementation
    }

    public ArrayList<T> getHeap() {
        // do not change because of backend-control
        return heap;
    }
    
    public void insert(T e) {
        
    }

//    public T remove() {
//        // TODO: implementation
//    }

    public void heapSort() {
        // TODO: implementation
    }

    public String toString() {
        // do not change because of backend-control
        return heap.toString();
    }

    public static void main(String[] args) {
        // TODO: test
    }
}
