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

import java.util.NoSuchElementException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>();
    }

    private class Iterator<Int> implements java.util.Iterator<Integer> {

        private Node cur = head;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Integer next() {
            if (cur == null)
                throw new NoSuchElementException();

            while (hasNext()) {
                Integer data = cur.getData();
                cur = cur.getNext();
                // is odd
                if (data % 2 == 1)
                    return data;
            }
            // Node can't save 'null' value so this is unique
            return null;
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
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
        test.push_back(2);

        for (Integer i : test)
            System.out.println(i);
    }

}
