/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training8;

import aud.util.Graphvizable;

/**
 * <pre>
 *         Vervollständigen sie die Klasse BTree um die Methode
 * 
 *         public boolean insert(Key key)
 * 
 *         zum Einfügen eines key in den BTree.
 *         Fügen Sie nacheinander in dieser Reihenfolge die Integer-Werte 7,19, 23, 4, 12, 17, 8, 11, 2 , 9 und 13 mit insert in einen anfangs leeren BTree der Ordnung 2 ein. Visualisieren Sie den BTree in den einzelnen Etappen mit dem DotViewer. Achtung: Visualisierung mit DotViewer beim Einreichen auskommentieren!
 *         Schreiben eine weitere Methode
 * 
 *         public int height()
 * 
 *         zur Ermittlung der Höhe des B-Baumes.
 *         Testen Sie diese Methode, indem Sie eine Millionen zufällig generierte Werte bei unterschiedlichen Seitengrößen in den B-Baum einfügen und die jeweilige Höhe bestimmen (ohne Visualisierung mit DotViewer!). 
 * 
 * Hinweis:
 * Die Klasse KTreeNode steht im Backend zur Verfügung und darf beim automatischen Test nicht importiert werden.
 * </pre>
 */
public class BTree<Key extends Comparable<Key>> implements Graphvizable {

    KTreeNode<Key> root_;
    int m_;

    /** create an empty tree of order 2*m+1 */
    public BTree(int m) {
        root_ = new KTreeNode<Key>();
        m_ = m;
        assert (m_ > 0);
    }

    /** get order of tree (maximum number of children) */
    int getOrder() {
        return 2 * m_ + 1;
    }

    /**
     * insert entry
     * 
     * @return {@code true} if {@code key} was not an entry of child before
     */
    public boolean insert(Key key) {
        return insert(this.root_, key);
    }

    protected boolean insert(KTreeNode<Key> node, Key key) {

        // It could be usefull to use the recursive structur to implement
        // insert.
        // This method can be empty if your implementation does not need it.
        return false;
    }

    public int height() {
        // TODO: calculate the height
        return 0;
    }

    public boolean contains(Key key) {
        return false;
    }

    public static void main(String[] args) {
        // TODO: Test and visualize your BTree
    }

    /**
     * find key in tree
     * 
     * @return found key, note that this is generally a <em>different</em>
     *         instance than {@code key}! (We have "only"
     *         {@code compareKeys()==0}.)
     */
    public Key find(Key key) {
        return root_.find(key);
    }

    public void split(KTreeNode<Key> node) {
        if (node.getK() > 2 * m_ + 1) { // CHEATING: we created and split a
            // (2*m+2)-node!
            node.split(m_ + 1);
            if (node.parent_ != null) {
                int i = node.parent_.getIndexOfChild(node);
                assert (i >= 0);
                node.parent_.mergeChild(i);
                node.parent_.checkConsistency();
                split(node.parent_); // eventually split parent
            }
        }
    }

    public String toDot() {
        return root_.toDot();
    }

    private String toString(KTreeNode<Key> _node) {
        String res = "";
        for (int i = 0; i < _node.getK(); ++i) {
            if (_node.getChild(i) != null) {
                res += toString(_node.getChild(i));
            }
        }
        return "[" + _node.toString() + " " + res + "] ";
    }

    public String toString() {
        return toString(root_);
    }
}