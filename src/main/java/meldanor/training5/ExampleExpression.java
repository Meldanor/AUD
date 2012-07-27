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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import aud.example.expr.ExpressionParser;
import aud.example.expr.ExpressionTree;
import aud.util.DotViewer;
import aud.util.Graphvizable;

/**
 * <pre>
 * Machen Sie sich mit dem ExpressionParser und ExpressionTree vertraut, indem Sie verschiedene arithmetische Ausdrücke (mit den 4 Grundrechenarten) testen:
 * 
 *     Geben Sie die Ausdrücke in den verschiedenen Ordnungen (inorder-, postorder und preorder) aus.
 *     Berechnen Sie die Werte der Ausdrücke.
 *     Visualisieren Sie die Ausdrücke mit dem DotViewer.
 * </pre>
 */
public class ExampleExpression {

    public static void main(String[] args) {

        // THE TASK
        commonStuff();

        // OK GUYS
        // THIS IS THE FINAL BATTLE
        // LETS DO SOME CRAZY PROGRAMMING STYLE
        // LETS CALCULATE THE PIZZA VOLUME
        // BUT WITH REFLECTION!
        try {
            // PIZZA VOLUME
            // Z = RADIUS
            int z = 15;
            // A = height
            int a = 5;
            // V = PI * Z * Z * A
            String thirdInput = Math.PI + "*" + z + "*" + z + "*" + a;
            calculatePizza(thirdInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void commonStuff() {
        String firstInput = "(42+-1)*" + Math.PI;
        ExpressionParser parser = new ExpressionParser();
        ExpressionTree first = parser.parse(firstInput);

        System.out.println(first.getValue());

        System.out.println("PreOrder:\t" + first.preorder());
        System.out.println("InOrder:\t" + first.inorder());
        System.out.println("PostOrder:\t" + first.postorder());
        System.out.println("LevelOrder:\t" + first.levelorder());
        DotViewer.displayWindow(first, firstInput);

        String secondInput = Math.PI + "/" + Math.E + "+0";
        ExpressionTree second = parser.parse(secondInput);
        System.out.println(second.getValue());

        System.out.println("PreOrder:\t" + second.preorder());
        System.out.println("InOrder:\t" + second.inorder());
        System.out.println("PostOrder:\t" + second.postorder());
        System.out.println("LevelOrder:\t" + second.levelorder());

        DotViewer.displayWindow(second, secondInput);
    }

    private static void calculatePizza(String input) throws Exception {

        System.out.println("Input\t\t" + input);

        // WE NEED THE CONSTRUCTOR FOR EXPRESSION PARSER
        Constructor<? extends ExpressionParser> constructor = ExpressionParser.class.getConstructor();
        // CREATE AN INSTANCE OF IT
        ExpressionParser parser = constructor.newInstance();
        // GET THE PARSE METHOD
        Method parseMethod = ExpressionParser.class.getMethod("parse", String.class);
        // CALL THE METHOD
        ExpressionTree pizzaTree = (ExpressionTree) parseMethod.invoke(parser, input);
        // GET THE GET VALUE METHOD
        Method getValueMethod = ExpressionTree.class.getMethod("getValue");
        // CALL THE METHOD
        Double value = (Double) getValueMethod.invoke(pizzaTree);

        System.out.println("Value\t\t" + value);

        // OK NOW THE ORDERS!
        // PREORDER FIRST
        Method preOrder = ExpressionTree.class.getMethod("preorder");
        String preOrderString = preOrder.invoke(pizzaTree).toString();
        System.out.println("PreOrder\t" + preOrderString);
        // INORDER SECOND
        Method inOrder = ExpressionTree.class.getMethod("inorder");
        String inOrderString = inOrder.invoke(pizzaTree).toString();
        System.out.println("InOrder\t\t" + inOrderString);
        // POSTORDER THIRD
        Method postOrder = ExpressionTree.class.getMethod("postorder");
        String postOrderString = postOrder.invoke(pizzaTree).toString();
        System.out.println("PostOrder\t" + postOrderString);
        // LEVELORDER LAST
        Method levelOrder = ExpressionTree.class.getMethod("levelorder");
        String levelOrderString = levelOrder.invoke(pizzaTree).toString();
        System.out.println("LevelOrder\t" + levelOrderString);

        // DISPLAY THE PIZZA TREE
        Method displayMethod = DotViewer.class.getMethod("displayWindow", Graphvizable.class, String.class);
        displayMethod.invoke(null, pizzaTree, input);
    }
}
