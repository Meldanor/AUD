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
 * <pre>
 * Schreiben Sie eine Klasse
 * 
 * public class ForwardList implements Iterable,
 * 
 * die Daten als einfach verkettete Liste verwaltet und rückwärts über diese iterieren kann, obwohl die Knoten keinen Rückwärtszeiger haben.
 * 
 *     Sie können sich dabei an dem Konzept der Klasse SList orientieren.
 *     Für den Backend-Test muss List eine Methode
 * 
 *     public void push_front(T obj)
 * 
 *     enthalten.
 *     Schreiben Sie zunächst eine rekursive Methode
 * 
 *     public void public void backTraverse(),
 * 
 *     die die Daten der Liste rückwärts ausgibt (Ausgabe mit System.out.print(...)).
 *     Schreiben Sie innerhalb der Klasse ForwardList die Klasse
 * 
 *     public class BackIterator implements java.util.Iterator,
 * 
 *     mit den üblichen Funktionen wie hasNext() und next(). Dabei soll die Iteration rückwärts über die Elemente der Liste erfolgen (unter Nutzung eines Stacks).
 *     Schreiben Sie die Funktion
 * 
 *     public BackIterator iterator(),
 * 
 *     in der eine Instanz Ihrer Klasse BackIterator zurück geliefert wird.
 * 
 *     Testen Sie Ihren BackIterator in main mit der foreach-Schleife.
 *     Beisiel:
 *     l sei [1,2,3,4,5]
 *     In der Schleife "for (int el : l){...}" werden dann die Elemente in der Reihenfolge 5,4,3, 3,2,1 durchlaufen.
 * </pre>
 */
public class ForwardList<T> implements Iterable<T> {

    private class Node {

        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }

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
        push_back(e);
    }

    public void addFirst(T e) {
        push_front(e);
    }

    public void addLast(T e) {
        push_back(e);
    }

    public void push_front(T e) {
        if (isEmpty())
            head = new Node(e);
        else {
            head = new Node(e, head);
        }
    }

    public void push_back(T e) {
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
        if (isEmpty())
            System.out.println("List is empty!");
        else
            backTraverse(head);
    }

    private void backTraverse(Node node) {
        if (node.hasNext())
            backTraverse(node.getNext());

        System.out.println(node.getData().toString());
    }

    /* /RECURSIVE BACK TRAVERSE */

    public class BackIterator<E> implements Iterator<T> {

        private Stack<T> stack = new Stack<T>();

        public BackIterator() {
            if (isEmpty())
                return;

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

    public static void main(String[] args) {
        ForwardList<Integer> list = new ForwardList<Integer>();
        for (int i = 1; i <= 5; ++i)
            list.add(i);

        for (int i : list)
            System.out.print(i + ",");
    }
}
