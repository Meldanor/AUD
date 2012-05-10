/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training4;

import java.io.File;

import meldanor.utils.Direction;
import meldanor.utils.Format;
import meldanor.utils.GraphVizUtils;

/**
 * @author Meldanor
 * 
 */
public class BuildBinTree {

    public static void test() {

        BinaryTree<Integer> minusEight = new BinaryTree<Integer>(-8);

        BinaryTree<Integer> four = new BinaryTree<Integer>(4);
        BinaryTree<Integer> one = new BinaryTree<Integer>(1);

        BinaryTree<Integer> six = new BinaryTree<Integer>(6);
        BinaryTree<Integer> minusEleven = new BinaryTree<Integer>(-11);
        BinaryTree<Integer> five = new BinaryTree<Integer>(5);
        BinaryTree<Integer> seven = new BinaryTree<Integer>(7);

        minusEight.setLeft(four);
        minusEight.setRight(one);

        four.setLeft(six);
        four.setRight(minusEleven);

        one.setLeft(five);
        one.setRight(seven);

        System.out.print("PreOrder:\t");
        for (BinaryTree<Integer> i : minusEight.preorder())
            System.out.print(i.getData() + ",");
        System.out.println();
        
        System.out.print("InOrder:\t");
        for (BinaryTree<Integer> i : minusEight.inorder())
            System.out.print(i.getData() + ",");
        System.out.println();
        
        System.out.print("PostOrder:\t");
        for (BinaryTree<Integer> i : minusEight.postorder())
            System.out.print(i.getData() + ",");
        System.out.println();
        
        System.out.print("LevelOrder:\t");
        for (BinaryTree<Integer> i : minusEight.levelorder())
            System.out.print(i.getData() + ",");

//        GraphVizUtils.visualize(minusEight.toDot(), new File("baum.png"), Format.PNG, Direction.BOTTOM_TOP);
    }

}
