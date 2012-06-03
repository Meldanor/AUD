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

import aud.RedBlackTree;
import aud.util.DotViewer;
import aud.util.SingleStepper;

/**
 * @author Meldanor
 * 
 */
public class RBTreeExample extends SingleStepper {

    private RedBlackTree<Integer, Integer> tree;
    protected DotViewer v = DotViewer.displayWindow((String) null, "BLA");

    public RBTreeExample(RedBlackTree<Integer, Integer> tree) {
        super("Bla");
        this.tree = tree;
    }

    @Override
    protected void onHalt() {
        if (tree != null)
            v.display(tree);
    }

    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>();
        RBTreeExample example = new RBTreeExample(tree);

        // @formatter:off

        tree.insert(6, null);    example.halt("6");
        tree.insert(7, null);    example.halt("7");
        tree.insert(3, null);    example.halt("3");
        tree.insert(4, null);    example.halt("4");
        tree.insert(2, null);    example.halt("2");
        tree.insert(1, null);    example.halt("1");
        example.halt("QUIT");

        // @formatter:on
    }
}
