/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training4;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author Meldanor
 * 
 */
public class ForwardList<T> implements Iterable<T> {

    private class Node {

        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }

        @SuppressWarnings("unused")
        public Node(T data, Node next) {
            this(data);
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;

    public ForwardList() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(T e) {
        push_front(e);
    }

    public void push_front(T e) {
        if (isEmpty())
            head = new Node(e);
        else {
            Node cur = head;
            while (cur.hasNext())
                cur = cur.getNext();

            cur.setNext(new Node(e));
        }
    }

    /* RECURSIVE BACK TRAVERSE */

    public void backTraverse() {
        backTraverse(head);
    }

    private void backTraverse(Node node) {
        if (node.hasNext()) {
            backTraverse(node.getNext());
            System.out.println(node.getData().toString());
        } else
            System.out.println(node.getData().toString());
    }

    /* /RECURSIVE BACK TRAVERSE */

    public class BackIterator<E> implements Iterator<T> {

        private Stack<T> stack = new Stack<T>();

        public BackIterator() {
            Node cur = head;
            while (cur.hasNext()) {
                stack.push(cur.getData());
                cur = cur.getNext();
            }
            stack.push(cur.getData());
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            return stack.pop();
        }

        @Override
        public void remove() {

        }
    }

    @Override
    public BackIterator<T> iterator() {
        return new BackIterator<T>();
    }

    public static void test() {
        ForwardList<Integer> list = new ForwardList<Integer>();
        for (int i = 1; i <= 5; ++i)
            list.add(i);

        for (int i : list)
            System.out.print(i + ",");
    }
}
