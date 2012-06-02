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

import aud.AVLTree;
import aud.util.DotViewer;
import aud.util.SingleStepper;

/**
 * @author Meldanor
 * 
 */
public class AVLExample extends SingleStepper {

    private AVLTree<Integer, Integer> tree;
    protected DotViewer v = DotViewer.displayWindow((String) null, "BLA");

    public AVLExample(AVLTree<Integer, Integer> tree) {
        super("Bla");
        this.tree = tree;
    }

    @Override
    protected void onHalt() {
        if (tree != null)
            v.display(tree);
    }

    public static void main(String[] args) {
        AVLTree<Integer, Integer> tree = new AVLTree<Integer, Integer>();
        AVLExample example = new AVLExample(tree);

        // @formatter:off

        tree.insert(14, null);    example.halt("14");
        tree.insert(15, null);    example.halt("15");
        tree.insert(17, null);    example.halt("17");
        tree.insert(7,  null);    example.halt("7");
        tree.insert(5,  null);    example.halt("5");
        tree.insert(10, null);    example.halt("10");
        tree.insert(16, null);    example.halt("16");
        example.halt("QUIT");

        // @formatter:on
    }
}
