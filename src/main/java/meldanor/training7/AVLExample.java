/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einh�lt:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training7;

import aud.AVLTree;
import aud.util.DotViewer;
import aud.util.SingleStepper;

/**
 * <pre>
 *     Erkl�ren Sie den Begriff AVL-Baum. Weshalb ist es erforderlich, bin�re B�ume auszugleichen?
 * 
 *     Erl�utern Sie den Algorithmus f�r das Einf�gen von Knoten in einen AVL-Baum am Beispiel des Einf�gens der nat�rlichen Zahlen 14, 15, 17, 7, 5, 10 und 16 in dieser Reihenfolge. Skizzieren Sie den AVL-Baum nach jedem Einf�gen eines Elementes und kontrollieren Sie Ihre Schritte mit dem Dotviewer und SingleStepper.
 * </pre>
 */
public class AVLExample extends SingleStepper {

    /*****************
     * a) Ein AVL Baum ist ein bin�rer Baum, dessen H�he vom linken und rechten
     * Teilbaum sich h�chstens um 1 unterscheiden. Es werden bin�re B�ume
     * ausgeglichen, da sonst B�ume nach vielen Einf�ge- und Entfernoperationen
     * zu einer Liste degenerieren k�nnen. Der Aufwand f�r eine Suche in so
     * einem Baum verkommt dann von O(log n) zu O(n). Um diesen entgegen zu
     * kommen, wird bei einem AVL Baum der folgende Algorithmus angewandt:
     * 
     * b) Der Knoten wird gem�� dem Algorithmus eines bin�ren Suchbaumes
     * eingef�gt. Danach wird �berpr�ft, ob die AVL Eigenschaft verletzt wird.
     * Wenn eine Verletzung vorliegt, wird dies per Rotation der Teilb�ume
     * wiederhergestellt. Beschreibung am Beispiel im Quellcode
     *********************/

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
        // Keine Verletzung
        tree.insert(14, null);    example.halt("14");
        // Keine Verletzung
        tree.insert(15, null);    example.halt("15");
        // Verletzung des Kriteriums - Rechter Teilbaum gr��er. Einmal Links rotieren
        tree.insert(17, null);    example.halt("17");
        // Keine Verletzung
        tree.insert(7,  null);    example.halt("7");
        // Verletzung des Kriteriums - Linker Teilbaum gr��er. Einmal rechts rotieren(von der sieben aus)
        tree.insert(5,  null);    example.halt("5");
        // Verletzung des Kriteriums - Linker Teilbaum gr��er. Zweimal links rotieren(von der vierzehn aus)
        // Dann Rechts rotieren
        tree.insert(10, null);    example.halt("10");
        // Verletzung des Kriteriums - Rechter Teilbaum von 15 gr��er. Einmal rechts rotieren und dann einmal links
        tree.insert(16, null);    example.halt("16");
        example.halt("QUIT");

        // @formatter:on
    }
}
