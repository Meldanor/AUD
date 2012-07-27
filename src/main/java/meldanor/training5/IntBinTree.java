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

import aud.BinaryTree;

/**
 * <pre>
 * Gegeben sei ein ungeordneter binärer Baum, dessen Knoten zufällig positive und negative ganze Zahlen als Schlüssel enthalten. Der Wert eines Teilbaums (das ist der vollständige Baum unter diesem Knoten) ist die Summe aller seiner Schlüsselwerte.
 * 
 *  Der Wert eines Pfades (das ist der Weg von der Wurzel des Baumes bis zu einem Blatt) ist die Summe aller Schlüsselwerte, die auf diesem Pfad liegen. Da auch negative Schlüssel erlaubt sind, können größere Teilbäume oder längere Pfade durchaus kleinere Werte haben als ihre Teilbäume.
 * 
 *  Notieren Sie für den vorgegebenen Baum die Werte der Teilbäume und die Werte der Pfade.
 *  Schreiben Sie die Java-Methoden
 *  public int height()
 * 
 *  für die Berechnung der Höhe des Teilbaumes,
 *  public int maxSum()
 * 
 *  für die Ermittlung des maximalen Teilbaums und
 *  public int maxPath()
 * 
 *  für das Finden des maximalen Pfadwertes von der Wurzel bis zu einem Blatt.
 * 
 * 
 * 
 *  Hinweise:
 * 
 *  Die Klasse BinaryTree<T> finden Sie unter den "Folien und Materialien" auf der Seite "Code/Beispiele".
 *  Für den Backendtest darf diese Klasse nicht importiert werden.
 *  Bei dem Backendtest müssen Sie auf die Visualisierung mit DotViewer und SingleStepper verzichten.
 * </pre>
 */
public class IntBinTree extends BinaryTree<Integer> {

    public IntBinTree(int data) {
        super(data);
    }

    public IntBinTree(int data, IntBinTree left, IntBinTree right) {
        super(data, left, right);
    }

    public int height() {
        return this.height(this);
    }

    private int height(BinaryTree<Integer> subTree) {
        if (subTree == null)
            return 0;
        return 1 + Math.max(height(subTree.getLeft()), height(subTree.getRight()));
    }

    public int maxSum() {

        int maxSum = Integer.MIN_VALUE;
        for (BinaryTree<Integer> subTree : this.preorder())
            maxSum = Math.max(maxSum, sum(subTree));

        return maxSum;
    }

    private int sum(BinaryTree<Integer> node) {

        int sum = node.getData();
        if (node.getLeft() != null)
            sum += sum(node.getLeft());
        if (node.getRight() != null)
            sum += sum(node.getRight());

        return sum;
    }

    public int maxPath() {
        Integer maxPath = Integer.MIN_VALUE;
        for (BinaryTree<Integer> node : this.preorder()) {
            if (node.isLeaf()) {
                maxPath = Math.max(maxPath, getPathSum(node));
            }
        }

        return maxPath;
    }

    private int getPathSum(BinaryTree<Integer> leaf) {
        int pathSum = leaf.getData();
        BinaryTree<Integer> node = leaf;
        while ((node = node.getParent()) != null)
            pathSum += node.getData();

        return pathSum;
    }

    public static void main(String[] args) {
        IntBinTree minusEight = new IntBinTree(-8);

        IntBinTree four = new IntBinTree(4);
        IntBinTree one = new IntBinTree(1);

        IntBinTree six = new IntBinTree(6);
        IntBinTree minusEleven = new IntBinTree(-11);
        IntBinTree five = new IntBinTree(5);
        IntBinTree seven = new IntBinTree(7);

        minusEight.setLeft(four);
        minusEight.setRight(one);

        four.setLeft(six);
        four.setRight(minusEleven);

        one.setLeft(five);
        one.setRight(seven);

        System.out.println("Height of root: " + minusEight.height());
        System.out.println("Height of node 'six': " + six.height());
        System.out.println("Height of node 'one': " + one.height());
        System.out.println("MaxSum of root: " + minusEight.maxSum());

        System.out.println("Sum of max path from the root: " + minusEight.maxPath());
    }
}
