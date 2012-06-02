/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training7;

/**
 * @author Meldanor
 *
 */
import java.util.Iterator;

import aud.Queue;

public class RangeTree<T extends Comparable<T>> extends SimpleTree<T> implements Iterable<T> {
    public T begin_, end_;

    public RangeTree(T b, T e) {
        super();
        begin_ = b;
        end_ = e;
    }

    public void setRange(T b, T e) {
        // Do not change (used for backend control)
        begin_ = b;
        end_ = e;
    }

    public class TreeIterator implements Iterator<T> {

        private Queue<T> dataQueue;

        public TreeIterator(SimpleTree<T> tree) {
            dataQueue = new Queue<T>();
            fillQueue(tree.getRoot());
        }

        public void fillQueue(Node node) {
            // CANCLE RECURSION
            if (node == null)
                return;

            // SAVE TEMPONARY
            int compareBegin = begin_.compareTo(node.getKey());
            int compareEnd = end_.compareTo(node.getKey());

            // CHECK LEFT TREE
            if (compareBegin < 0)
                fillQueue(node.getLeft());

            // ELEMENT IS IN RANGE -> ENQUEUE
            if (compareBegin <= 0 && compareEnd >= 0)
                dataQueue.enqueue(node.getKey());

            // CHECK RIGHT TREE
            if (compareEnd > 0)
                fillQueue(node.getRight());

        }

        @Override
        public boolean hasNext() {
            return !dataQueue.is_empty();
        }

        @Override
        public T next() {
            return dataQueue.dequeue();
        }

        @Override
        public void remove() {
        }

    }

    public Iterator<T> iterator() {
        return new TreeIterator(this);
    }

    public static void main(String[] args) {
        RangeTree<Integer> tree = new RangeTree<Integer>(7, 11);
        tree.insert(9);
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(13);
        tree.insert(11);
        tree.insert(15);

        System.out.println(tree.toString());

        for (Integer i : tree)
            System.out.print(i + " ");

    }
}
