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

/**
 * @author Meldanor
 *
 */

import aud.BinaryTree;

public class MyBinTree<T> extends BinaryTree<T> {

    public MyBinTree(T data) {
        super(data);
    }

    public MyBinTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
        super(data, left, right);
    }

    public int maxWidth() {

        int height = height();
        int maxWidth = Integer.MIN_VALUE;
        int curWidth = 0;
        // GO THROUGH EVERY HEIGHT
        for (int i = 0; i < height; ++i) {
            // COUNT TREE WHICH HAS THE NIVEAU i
            for (BinaryTree<T> subtree : this.levelorder()) {
                // HAS THE NIVEAU
                if (getNiveau(subtree) == i)
                    ++curWidth;
            }
            maxWidth = Math.max(maxWidth, curWidth);
            curWidth = 0;
        }

        return maxWidth;
    }

    public int height() {
        return this.height(this);
    }

    private int height(BinaryTree<T> subTree) {
        if (subTree == null)
            return 0;
        return 1 + Math.max(height(subTree.getLeft()), height(subTree.getRight()));
    }

    private int getNiveau(BinaryTree<T> node) {
        BinaryTree<T> cur = node;
        int niveau = 0;
        while ((cur = cur.getParent()) != null)
            ++niveau;

        return niveau;
    }

    public static void main(String[] args) {

        // TREE FROM TASK
        MyBinTree<Integer> one = new MyBinTree<Integer>(1);
        MyBinTree<Integer> two = new MyBinTree<Integer>(2);
        MyBinTree<Integer> three = new MyBinTree<Integer>(3);
        MyBinTree<Integer> four = new MyBinTree<Integer>(4);
        MyBinTree<Integer> five = new MyBinTree<Integer>(5);
        MyBinTree<Integer> six = new MyBinTree<Integer>(6);
        MyBinTree<Integer> seven = new MyBinTree<Integer>(7);

        one.setLeft(two);
        two.setLeft(three);
        two.setRight(four);
        four.setLeft(five);
        one.setRight(six);
        six.setRight(seven);

        // 3
        System.out.println(one.maxWidth());
    }

}
