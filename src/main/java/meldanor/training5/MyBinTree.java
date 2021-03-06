/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einh�lt:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training5;

import java.util.ArrayList;
import java.util.List;

import aud.BinaryTree;

/**
 * <pre>
 * Schreiben Sie die Klasse MyBinTree als Erweiterung der Klasse BinaryTree<T>.
 * 
 * Die Methode
 * 
 * public int maxWidth(),
 * 
 * soll f�r den gegebenen bin�ren (Teil-)Baum die gr��te Breite (d.h. die maximale Anzahl von Knoten in der gleichen Ebene) zur�ck geben.
 * 
 * F�r folgenden Bin�rbaum ist z. B. drei die gr��te Breite.
 * 
 * 
 * Hinweise:
 * 
 *     Die Klasse BinaryTree<T> finden Sie unter den "Folien und Materialien" auf der Seite "Code/Beispiele".
 *     F�r den Backendtest darf diese Klasse nicht importiert werden.
 *     Bei dem Backendtest m�ssen Sie auf die Visualisierung mit DotViewer und SingleStepper verzichten.
 * </pre>
 */
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

        List<BinaryTree<T>> subTrees = new ArrayList<BinaryTree<T>>();
        for (BinaryTree<T> subTree : this.levelorder())
            subTrees.add(subTree);

        int curWidth = 0;
        // GO THROUGH EVERY HEIGHT
        for (int i = 0; i < height; ++i) {
            // COUNT TREE WHICH HAS THE NIVEAU i
            for (BinaryTree<T> subtree : subTrees) {
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
