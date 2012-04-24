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
 * @author Meldanor
 * 
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

    public static void test() {
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
