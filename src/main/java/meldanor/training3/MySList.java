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
import java.util.Random;

/**
 * <pre>
 * Schreiben Sie eine Klasse MySList, die ganzzahligen Daten als einfach verkettete Liste verwaltet und über die ungeraden Zahlen iterieren kann.
 * 
 *     Sie können sich dabei an dem Konzept der Klasse SList orientieren.
 *     Für den Backend-Test muss MySList eine Methode
 * 
 *     public void push_back(int obj)
 * 
 *     enthalten.
 *     Schreiben Sie innerhalb der Klasse MySList die Klasse
 * 
 *     public class Iterator implements java.util.Iterator,
 * 
 *     mit den üblichen Funktionen wie hasNext() und next(). Dabei soll die Iteration nur über die ungeraden Elemente einer Liste von ganzen Zahlen erfolgen.
 *     Schreiben Sie die Funktion
 * 
 *     public Iterator iterator(),
 * 
 *     in der eine Instanz Ihrer Klasse Iterator zurück geliefert wird.
 * 
 *     Testen Sie Ihren Iterator in main mit der foreach-Schleife.
 *     Beisiel:
 *     l sei [85,72,93,81,74,42]
 *     In der Schleife "for (int el : l){...}" dürfen dann nur die Elemente 85, 93 und 81 durchlaufen werden.
 * 
 * Hinweis:
 * Die Klasse SList finden Sie unter den "Folien und Materialien" auf der Seite "Code/Beispiele".
 * </pre>
 */
public class MySList implements Iterable<Integer> {

    private class Node {

        private Integer data;
        private Node next;

        public Node(int e) {
            this.data = e;
        }

        public Integer getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head = null;

    public MySList() {
        // Empty List
    }

    public void push_back(int e) {
        // Is Empty
        if (head == null)
            head = new Node(e);
        else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.setNext(new Node(e));
        }

    }

    public void clear() {
        head = null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>();
    }

    private class Iterator<Int> implements java.util.Iterator<Integer> {

        private Node cur;

        public Iterator() {
            this.cur = head;
            // HEAD IS EVEN
            if (!isOdd(cur)) {
                // SET HEAD
                cur = getOddNode(cur);
            }
        }
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Integer next() {
            int data = cur.getData();
            cur = getOddNode(cur);
            return data;
        }

        private Node getOddNode(Node start) {

            Node oddNode = start;
            while ((oddNode = oddNode.getNext()) != null) {
                if (isOdd(oddNode))
                    return oddNode;
            }
            return oddNode;

        }

        private boolean isOdd(Node node) {
            return Math.abs(node.getData() % 2) == 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        MySList test = new MySList();

        test.push_back(85);
        test.push_back(72);
        test.push_back(93);
        test.push_back(81);
        test.push_back(74);
        test.push_back(42);
        test.push_back(3);
        test.push_back(4);
        test.push_back(4);

        // 85 93 81 3
        for (Integer i : test)
            System.out.print(i + ",");

        test.clear();

        // RANDOM ARRAY
        Random rand = new Random();
        int tests = 32;
        int[] array = new int[tests];

        for (int i = 0; i < tests; ++i)
            array[i] = rand.nextInt(tests * 31);

        Arrays.sort(array);

        for (int i = 0; i < tests; ++i)
            test.push_back(array[i]);

        System.out.println(Arrays.toString(array));
        System.out.print('[');
        for (Integer i : test)
            System.out.print(i + ", ");
        System.out.print(']');

    }

}
