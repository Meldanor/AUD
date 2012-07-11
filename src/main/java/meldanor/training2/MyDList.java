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

import java.util.Iterator;

import aud.DList;

/**
 * <pre>
 * Aus der Vorlesung kennen sie die doppelt verkettete Liste DList. Erweitern Sie diese Klasse um:
 * 
 *     eine Instanzmethode
 * 
 *     public void append(MyDList li)
 * 
 *     zum Verketten zweier Listen. Dabei soll die aufrufende Liste ver�ndert werden, die Liste li aber nicht.
 *     und
 *     um die Instanzmethode
 * 
 *     public void insert(int n, MyDList li)
 * 
 *     zum Einbetten der Liste li in die aufrufende Liste ab Position n (nullbasiert).
 *     Testen Sie beide Methoden an geeigneten Beispielen.
 * 
 * Hinweise:
 * 
 *     Wird eine Liste an sich selbst angeh�ngt, oder in sich selbst eingef�gt, soll ihr Inhalt kopiert werden. Beispiel: li = [a, b]
 *     li.append(li) == [a, b, a, b]
 *     Hier finden sie DList und Graphvizable.
 *     Die Klasse DList steht im Backend zur Verf�gung und darf nicht importiert werden.
 * </pre>
 */
public class MyDList<T> extends DList<T> {

    public MyDList() {
        super();
    }

    public void append(MyDList<T> list) {
        // IF APPENDING LIST TO ITSELF CREATE A COPY
        if (this == list)
            list = copy(list);

        // INSERT ELEMENTS OF COPY AT THE END
        Iterator<T> iter = list.iterator();
        T data = null;
        // ITERATE OVER COMPLETE LIST
        while (iter.hasNext()) {
            data = iter.next();
            this.push_back(data);
        }

    }

    public void insert(int n, MyDList<T> list) {
        // WHEN THIS IS EMPTY OR N NOT IN THE LIST SIZE DO A APPEND
        if (n < 0 || n >= this.size() - 1)
            append(list);
        else {
            // CREATE A COPY
            if (this == list)
                list = copy(list);
            ForwardIterator iter = this.iterator();
            for (int i = 0; i <= n; ++i)
                iter.next();

            while (!list.empty()) {
                this.insert_before(iter, list.front());
                list.pop_front();
            }
        }
    }

    // CREATE A COPY OF THE LIST
    private MyDList<T> copy(MyDList<T> list) {
        MyDList<T> copy = new MyDList<T>();
        Iterator<T> iter = list.iterator();
        T data = null;
        while (iter.hasNext()) {
            data = iter.next();
            copy.push_back(data);
        }
        return copy;
    }

    public static void main(String[] args) {
        // 0 - 9
        MyDList<Integer> list1 = new MyDList<Integer>();
        for (int i = 0; i < 10; ++i)
            list1.push_back(i);

        // 10 - 19
        MyDList<Integer> list2 = new MyDList<Integer>();
        for (int i = 10; i < 20; ++i)
            list2.push_back(i);

        list1.append(list2);

        for (Integer i : list1)
            System.out.print(i + ";");
        // 0;1;2;3;4;5;6;7;8;9;10;11;12;13;14;15;16;17;18;19;

        System.out.println("\n");

        list1.clear();
        list1.push_back(0);
        list1.push_back(1);
        list1.push_back(2);
        list1.push_back(3);
        list1.push_back(8);
        // LIST 1 = 0;1;2;3;8

        list2.clear();
        list2.push_back(4);
        list2.push_back(5);
        list2.push_back(6);
        list2.push_back(7);
        // LIST 2 = 4;5;6;7

        System.out.println(list1.size());
        System.out.println(list2.size());

        list1.insert(3, list2);

        for (Integer i : list1)
            System.out.print(i + ";");
        // 0;1;2;3;4;5;6;7;8;
    }

    // BETTER ALGORITHMS
//    public void append(MyDList<T> that) {
//
//        int size = that.size();
//        int i = 0;
//        Iterator<T> iter = that.iterator();
//        while (i < size) {
//            this.push_back(iter.next());
//            ++i;
//        }
//
//    }
//
//    public void insert(int n, MyDList<T> that) {
//
//        if (n > this.size() || n < 0)
//            append(that);
//
//        ForwardIterator thisIter = this.iterator();
//        for (int i = 0; i < n; i++) {
//            thisIter.next();
//        }
//
//        int i = 0;
//        int size = that.size();
//        Iterator<T> thatIter = that.iterator();
//        while (i++ < size) {
//            this.insert_after(thisIter, thatIter.next());
//            thisIter.next();
//        }
//
//    }
}
