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

import java.util.Iterator;

import aud.SList;

/**
 * @author Meldanor
 * 
 */
public class IntSList {

    // FILTER FOR ALL EVEN NUMBERS
    private static Predicate FILTER_EVEN = new Predicate() {
        @Override
        public boolean test(int el) {
            return el % 2 == 0;
        }
    };

    private final SList<Integer> list;

    public IntSList() {
        list = new SList<Integer>();
    }

    public String toString() {
        return list.toString();
    }

    public void push_back(int obj) {
        list.push_back(obj);
    }

    public IntSList filter(Predicate p) {
        IntSList result = new IntSList();

        Iterator<Integer> iter = this.list.iterator();
        Integer e;
        while (iter.hasNext()) {
            e = iter.next();
            if (p.test(e))
                result.push_back(e);
        }

        return result;
    }

    public static void test() {

        IntSList test = new IntSList();
        test.push_back(85);
        test.push_back(72);
        test.push_back(93);
        test.push_back(81);
        test.push_back(74);
        test.push_back(42);
        System.out.println(test.toString());

        IntSList filtered = test.filter(FILTER_EVEN);

        System.out.println(filtered);
    }
}
