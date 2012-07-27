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
 * <pre>
 * In dieser Aufgabe sollen Objekte in eine Liste geordnet eingefügt werden. Erklären Sie kurz, wie das Einfügen in die geordnete Liste erfolgt.
 * 
 *     Schreiben Sie eine Klasse
 * 
 *     public class SortList<T extends Comparable<T>>
 * 
 *         mit dem Attribut
 *         SList<T> list,
 *         einem Konstruktor, der eine leere Liste list erzeugt und
 *         Schreiben Sie eine Methode
 * 
 *         public boolean insert(T obj),
 * 
 *         die ein neues Element obj sortiert in die Liste list einfügt. Der Vergleich zweier Objekte vom Typ T erfolgt mit obj1.compareTo(obj2). Es sollen keine doppelten Werte in die Liste aufgenommen werden. Ist ein Wert bereits in der Liste enthalten, soll false zurückgegeben werden, im anderen Fall true.
 *         Testen Sie SortList für Zufallszahlen vom Typ int.
 *     Geben Sie die Vor- und Nachbedingungen Ihres insert-Algorithmus an. Wie lautet die Schleifen-Invariante?
 * 
 * Hinweise:
 * 
 *     Die Methode
 * 
 *       public String toString() {
 *             return list.toString();
 *       }
 * 
 *     muss wegen der Backend-Kontrolle in der Klasse SortList enthalten sein.
 *     Die Klasse SList finden Sie unter den "Folien und Materialien" auf der Seite "Code/Beispiele". Die Klasse SList steht im Backend zur Verfügung und darf für die automatische Backend-Kontrolle nicht importiert werden.
 * </pre>
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

    public static void main(String[] args) {
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
