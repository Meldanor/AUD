/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import aud.SList;

/**
 * @author Meldanor
 * 
 */
public class SortList<T extends Comparable<T>> {

    private final SList<T> list;

    public SortList() {
        list = new SList<T>();
    }

    public boolean insert(T e) {

        Iterator<T> iter = list.iterator();
        T cur = null;
        // ITERATE OVER LIST
        for (int i = 0; iter.hasNext(); ++i) {
            cur = iter.next();
            // EQUALS = equivalence relation
            // COMPARE TO = natural order
            // equals != compareTo
            if (e.equals(cur))
                return false;

            // FOUND ELEMENT
            if (e.compareTo(cur) < 0) {
                list.insert(i, e);
                return true;
            }
        }

        // element is highest in the list
        list.push_back(e);
        return true;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static void test() {
        int tests = 100;

        SortList<Integer> test = new SortList<Integer>();
        Random rand = new Random();
        int[] array = new int[tests];
        for (int i = 0; i < tests; ++i) {
            array[i] = rand.nextInt(tests * 31);
            if (!test.insert(array[i]))
                System.out.println(array[i] + " twice!");

        }

        System.out.println(Arrays.toString(array));
        System.out.println(test.toString().replaceAll(",", ", "));

    }
}
