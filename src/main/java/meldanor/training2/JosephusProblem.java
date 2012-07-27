/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einh�lt:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import aud.Queue;

/**
 * <pre>
 * Von dem Geschichtsschreiber Josephus (1. Jhd.) ist uns ein interessantes mathematisches Problem �berliefert:
 * 
 * n Personen stehen in einem Kreis und werden solange mit einem k-silbigen Abz�hlvers ausgez�hlt, bis nur noch eine bestimmte Anzahl von Personen �brig bleibt. Josephus interessierte wie er und seine Freunde stehen m�ssen, damit sie alleine �brig bleiben.
 * 
 * L�sen Sie das Josephus-Problem mit Hilfe der Klasse Queue.
 * 
 *     Beschreiben Sie zun�chst die L�sungsidee kurz mit Worten.
 *     Schreiben Sie in der Klasse JosephusProblem eine Methode
 * 
 *     public static Queue<T> josephus(T[] children, int numbSyl),
 * 
 *     die eine Liste der Personen und die Anzahl der Silben �bergeben bekommt und die vollst�ndige Liste der "Ausgez�hlten" als Queue<T< zur�ck gibt (die Queue enth�lt also alle Personen in der "ausgez�hlten" Reihenfolge).
 *     Es sollen 13 Personen mit einem 7-silbigen Abz�hlreim ausgez�hlt werden. An welche Positionen m�ssen sich Josephus und seine f�nf Freunde stellen, damit sie �brig bleiben?
 * 
 * Hinweise:
 * 
 *     Hier finden sie AbstractQueue und Queue.
 *     Die Klasse Queue steht im Backend zur Verf�gung und darf nicht importiert werden.
 * </pre>
 */
public class JosephusProblem {

    /*
     * a) Zufaellig die Leute aufstellen und auszaehlen lassen. Danach pruefen,
     * ob in den ersten 7 Ausgezaehlten sich ein Freund von Josephus oder er
     * selbst befindet. Ist dies nicht der Fall, so ist dies eine gueltige
     * Aufstellung. Befindet sich einer, die Leute zufaellig neu aufstellen
     * lassen und wieder pruefen. Solange dies durchfuehren, bis eine gueltige
     * Aufstellung gefunden wurde.
     * 
     * c) An den Position (von 1 beginnend zu zaehlend): 2,3,5,6,8,12
     */

    public static <T> Queue<T> josephus(T[] children, int numbSyl) {

        Queue<T> queue = new Queue<T>();
        // create a copy
        T[] copy = Arrays.copyOf(children, children.length);

        // i is gobal count, j is sylb counter and k is counter for left people
        for (int i = 0, j = 1, k = 0; k < children.length; ++i) {
            if (j == numbSyl && copy[i % copy.length] != null) {
                j = 1;
                queue.enqueue(copy[i % copy.length]);
                copy[i % copy.length] = null;
                ++k;
            } else if (copy[i % copy.length] != null)
                ++j;
        }

        return queue;
    }

    private static Set<String> surviver = new HashSet<String>(8);

    public static void main(String[] args) {
        // people who have to survive
        surviver.add("Josepheus");
        surviver.add("Marius");
        surviver.add("Julius");
        surviver.add("Claudius");
        surviver.add("Augustus");
        surviver.add("Titus");

        List<String> all = new LinkedList<String>();
        all.addAll(surviver);
        // victims
        all.add("Gandalf");
        all.add("Yoda");
        all.add("Son-Goku");
        all.add("Link");
        all.add("Shepard");
        all.add("Leonidas");
        all.add("Cthuhlu");

        Queue<String> queue = null;
        LinkedList<String> list = new LinkedList<String>();
        Collections.shuffle(all);

        do {
            list.clear();
            Collections.shuffle(all);
            queue = josephus((String[]) all.toArray(new String[all.size()]), 7);
            while (!queue.is_empty())
                list.add(queue.dequeue());

        } while (!check(list));

        for (String person : all)
            System.out.print(person + " , ");
        System.out.println();

        for (int i = 0; i < all.size(); ++i)
            if (surviver.contains(all.get(i)))
                System.out.print((i + 1) + ",");
        System.out.println();

        for (String person : list)
            System.out.print(person + " , ");
    }

    // � Tabea
    public static <T> Queue<T> josephus2(T[] children, int numbSyl) {

        Queue<T> reihe = new Queue<T>();
        Queue<T> raus = new Queue<T>();

        // Array to queue
        for (int i = 0; i < children.length; ++i) {
            reihe.enqueue(children[i]);
        }

        while (!reihe.is_empty()) {
            // erstes element an letzte stelle setzen
            for (int i = 0; i < numbSyl - 1; ++i)
                reihe.enqueue(reihe.dequeue());
            // typen kicken
            raus.enqueue(reihe.dequeue());
        }

        return raus;

    }

    private static boolean check(LinkedList<String> person) {

        // FIRST 8 PERSON MUSN'T CONTAINS A ALLOWED SURVIVER
        for (int i = 0; i < 7; ++i)
            if (surviver.contains(person.get(i)))
                return false;

        // LAST 5 PERSONS ARE ALLOWED SURVIVER
        return true;
    }
}
