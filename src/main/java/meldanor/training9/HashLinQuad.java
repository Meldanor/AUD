/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training9;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Meldanor
 * 
 */
public class HashLinQuad {
    // simple Hashtable with linear/quadratic probing
    // capacity is big enough
    private int[] table;
    private int size; // current number of elements
    private int capacity;

    private int loadFactor;

    private static final int MIN_SIZE = 13;

    public HashLinQuad() {
        this(MIN_SIZE);
    }

    private HashLinQuad(int capacity) {
        this.capacity = capacity;
        table = new int[capacity];
        loadFactor = (int) (capacity * 0.75);
    }

    private int hash(int obj) {
        return Math.abs(obj) % capacity;
    }

    public int addLin(int obj) {
        // WE NEED TO RESIZE THE HASH TABLE
        if (size >= loadFactor)
            resize();

        // COUNTER FOR COLLISIONS
        int collisions = 0;

        // SEARCH FOR FREE SPACE
        int i;
        for (i = hash(obj); table[i] != 0; i = (i + 1) % capacity)
            ++collisions;

        // INSERT VALUE
        table[i] = obj;
        ++size;

        return collisions;
    }

    public int addQuad(int obj) {
        // WE NEED TO RESIZE THE HASH TABLE
        if (size >= loadFactor)
            resize();

        // COUNTER FOR COLLISIONS
        int collisions = 0;

        // SEARCH FOR FREE SPACE
        int i = 0;
        int hash = hash(obj);
        int temp = hash;
        while (table[temp] != 0) {
            ++collisions;
            ++i;
            temp = Math.abs((hash + (i * i))) % capacity;
        }

        // INSERT VALUE
        table[temp] = obj;
        ++size;

        return collisions;
    }

    private void resize() {
        // TEMP HASH MAP WITH TWICE SIZE
        HashLinQuad t = new HashLinQuad(size << 1);
        for (int i = 0; i < size; ++i) {
            // RE-HASH
            if (table[i] != 0)
                t.addQuad(table[i]);

        }

        this.capacity = t.capacity;
        this.size = t.size;
        this.table = t.table;
        this.loadFactor = t.loadFactor;

    }

    public String toString() {
        return Arrays.toString(table);
    }

    private final static int AMOUNT_OF_TESTS = 1000;

    public static void main(String[] args) {

        // FILL TEST NUMBERS
        Random rand = new Random();
        int[] testNumbers = new int[AMOUNT_OF_TESTS];
        for (int i = 0; i < 1000; ++i)
            testNumbers[i] = rand.nextInt(50000);

        // FILL HASH TABLE WITH LINEAR SON
        HashLinQuad hashTable = new HashLinQuad();
        int collisions = 0;
        for (int i = 0; i < testNumbers.length; ++i)
            collisions += hashTable.addLin(testNumbers[i]);

        System.out.println(hashTable);
        System.out.println("Collisions=" + collisions);

        // FILL HASH TABLE WITH SQUARE SON
        hashTable = new HashLinQuad();
        collisions = 0;
        for (int i = 0; i < testNumbers.length; ++i)
            collisions += hashTable.addQuad(testNumbers[i]);

        System.out.println(hashTable);
        System.out.println("Collisions=" + collisions);
    }
}
