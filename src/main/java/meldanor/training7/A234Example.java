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

import aud.A234Tree;
import aud.util.DotViewer;
import aud.util.SingleStepper;

/**
 * @author Meldanor
 * 
 */
public class A234Example extends SingleStepper {

    private A234Tree<Integer> tree;
    protected DotViewer v = DotViewer.displayWindow((String) null, "BLA");

    public A234Example(A234Tree<Integer> tree) {
        super("BLA");
        this.tree = tree;
    }

    @Override
    protected void onHalt() {
        if (tree != null)
            v.display(tree);
    }

    public static void main(String[] args) {
        A234Tree<Integer> tree = new A234Tree<Integer>();
        A234Example example = new A234Example(tree);

        // @formatter:off

        tree.insert(5);     example.halt("5");
        tree.insert(1);     example.halt("1");
        tree.insert(19);    example.halt("19");
        tree.insert(25);    example.halt("25");
        tree.insert(17);    example.halt("17");
        tree.insert(21);    example.halt("21");
        tree.insert(9);     example.halt("9");
        tree.insert(15);    example.halt("15");
        tree.insert(14);    example.halt("14");

        example.halt("QUIT");

        // @formatter:on

        A234Tree<Integer> tree2 = new A234Tree<Integer>();
        A234Example example2 = new A234Example(tree2);

        // @formatter:off

        tree2.insert(25);   example2.halt("5");
        tree2.insert(9);    example2.halt("1");
        tree2.insert(14);   example2.halt("19");
        tree2.insert(1);    example2.halt("25");
        tree2.insert(17);   example2.halt("17");
        tree2.insert(21);   example2.halt("21");
        tree2.insert(15);   example2.halt("9");
        tree2.insert(5);    example2.halt("15");
        tree2.insert(19);   example2.halt("14");

        example2.halt("QUIT");

        // @formatter:on
    }

}
