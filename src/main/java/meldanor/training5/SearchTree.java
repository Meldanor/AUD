/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training5;

import java.util.Iterator;

import aud.BinaryTree;

/**
 * @author Meldanor
 * 
 */
public class SearchTree<T> extends BinaryTree<T> {

    public SearchTree(T data) {
        super(data);
    }

    public SearchTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
        super(data, left, right);
    }

    // Maximum im Suchbaum
    // anzahl der Knoten

    public static void main(String[] args) {

    }

    public int sizeRec() {
        return sizeRec(this);
    }

    private int sizeRec(BinaryTree<T> node) {
        if (node == null)
            return 0;
        else
            return 1 + sizeRec(node.getLeft()) + sizeRec(node.getRight());
    }

    public int sizeIter() {
        int counter = 0;
        Iterator<BinaryTree<T>> trees = this.preorder().iterator();
        while (trees.hasNext())
            ++counter;

        return counter;
    }

    public T maxIter() {

        BinaryTree<T> node = this;
        while (node.getRight() != null)
            node = node.getRight();

        return node.getData();
    }

    public T maxRec() {
        return maxRec(this);
    }

    private T maxRec(BinaryTree<T> node) {
        if (node.getRight() != null)
            return node.getData();
        else
            return maxRec(node.getRight());
    }
}
