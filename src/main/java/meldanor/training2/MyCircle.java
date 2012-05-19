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
        if (head_ == null)
            return null;
        else
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
        if (empty())
            return 0;
        int count = 1;
        Node start = head_;
        while (start.next_ != head_) {
            start = start.next_;
            ++count;
        }
        return count;
    }

    public boolean empty() {

        return head_ == null;
    }

    public void push_back(T obj) {
        // When list is empty
        if (head_ == null) {
            head_ = new Node(obj, null, null);
            head_.next_ = head_;
            head_.prev_ = head_;
        } else {
            Node temp = head_.prev_;
            Node insert = new Node(obj, temp, head_);
            temp.next_ = insert;
            head_.prev_ = insert;
        }
    }
    public void pop_front() {
        // DO NOTHNG IN AN EMPTY RING
        if (empty())
            return;
        if (size() == 1) {
            head_ = null;
        } else {
            Node temp = head_;
            Node temp2 = head_.next_;
            head_ = temp2;
            temp2.prev_ = temp.prev_;
            temp.prev_.next_ = temp2;
        }
    }

    public static void main(String[] args) {
        MyCircle<String> circle = new MyCircle<String>();
        // 0
        System.out.println(circle.size());
        // TRUE
        System.out.println(circle.empty());

        String quote = "Bond James Bond";
        String[] split = quote.split(" ");
        for (String s : split)
            circle.push_back(s);

        // [Bond,James,Bond]
        System.out.println(circle.toString());
        // 3
        System.out.println(circle.size());
        // FALSE
        System.out.println(circle.empty());

        circle.pop_front();
        // [James,Bond]
        System.out.println(circle.toString());
        // 2
        System.out.println(circle.size());
        // FALSE
        System.out.println(circle.empty());

    }
}
