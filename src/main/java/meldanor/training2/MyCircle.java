/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training2;

/**
 * @author Meldanor
 * 
 */
public class MyCircle<T> {

    class Node {
        T data_ = null;
        Node next_ = null;
        Node prev_ = null;

        Node(T obj, Node prv, Node nxt) {
            data_ = obj;
            prev_ = prv;
            next_ = nxt;
        }
    }

    protected Node head_ = null;

    public MyCircle() {
        // do not change
        head_ = null;
    }

    public T front() {
        return head_.data_;
    }

    public String toString() {
        // do not change (because of backend-control)
        // have changed it - use stringBuilder instead of concating strings
        if (empty())
            return "[]";
        StringBuilder sBuilder = new StringBuilder(1024);
        sBuilder.append('[');
        Node node = head_;
        do {
            sBuilder.append(node.data_.toString());
            if (node.next_ != head_)
                sBuilder.append(',');
            node = node.next_;
        } while (node != head_);
        sBuilder.append(']');
        return sBuilder.toString();
    }

    public int size() {
        int count = 0;
        Node start = head_;
        while (start.next_ != head_)
            ++count;
        return count;
    }

    public boolean empty() {
        return head_.next_ == head_;
    }

    public void push_back(T obj) {
        Node temp = head_.prev_;
        Node insert = new Node(obj, temp, head_);
        temp.next_ = insert;
        head_.prev_ = insert;
    }

    public void pop_front() {
        // DO NOTHNG IN AN EMPTY RING
        if (empty())
            return;
        Node temp = head_;
        Node temp2 = head_.next_;
        head_ = temp2;
        temp2.prev_ = temp.prev_;
        temp.prev_.next_ = temp2;

    }

    public static void test() {
        // TODO: test
    }
}
