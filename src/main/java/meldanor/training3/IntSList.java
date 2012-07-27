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
 * <pre>
 *     Schreiben Sie eine Klasse
 * 
 *     public class IntSList {
 *        SList li;
 *        ...
 *     }
 * 
 *     mit dem Attribut li. Diese Klasse soll eine einfach verkettete Liste mit ganzzahligen Daten erstellen und filtern können. Sie können Funktionen aus SList nutzen, um eine derartige Liste aufzubauen, zum Beispiel:
 * 
 *     public void push_back(int obj){
 *          li.push_back(obj);
 *     }
 *     Schreiben Sie eine Funktion
 * 
 *     public IntSList filter(Predicate p),
 * 
 *     die ein Prädikat p übergeben bekommt und alle Elemente - die dieses Prädikat erfüllen - in eine neue Liste aufsammelt (in der gefundenen Reihenfolge). Beachten Sie, dass es für li einen Iterator gibt (in SList definiert). Das Inteface
 * 
 *     public interface Predicate {
 *        public boolean test(int el);
 *     }
 * 
 *     ist gegeben.
 *     Zum Test sollen aus der ganzzahligen Liste alle geraden Elemente heraus gefiltert werden.
 *     Beisiel:
 *     l sei [85,72,93,81,74,42]
 *     l.filter(p) liefert dann die Liste [72,74,42]. Wie muss p definiert werden? Gibt es verschiedene Varianten?
 * 
 * Hinweis:
 * Die Klasse SList finden Sie unter den "Folien und Materialien" auf der Seite "Code/Beispiele".
 * </pre>
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

    public static void main(String[] args) {

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
