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
 * <pre>
 *     Erklären Sie den Begriff AVL-Baum. Weshalb ist es erforderlich, binäre Bäume auszugleichen?
 * 
 *     Erläutern Sie den Algorithmus für das Einfügen von Knoten in einen AVL-Baum am Beispiel des Einfügens der natürlichen Zahlen 14, 15, 17, 7, 5, 10 und 16 in dieser Reihenfolge. Skizzieren Sie den AVL-Baum nach jedem Einfügen eines Elementes und kontrollieren Sie Ihre Schritte mit dem Dotviewer und SingleStepper.
 * </pre>
 */
public class AVLExample extends SingleStepper {

    /*****************
     * a) Ein AVL Baum ist ein binärer Baum, dessen Höhe vom linken und rechten
     * Teilbaum sich höchstens um 1 unterscheiden. Es werden binäre Bäume
     * ausgeglichen, da sonst Bäume nach vielen Einfüge- und Entfernoperationen
     * zu einer Liste degenerieren können. Der Aufwand für eine Suche in so
     * einem Baum verkommt dann von O(log n) zu O(n). Um diesen entgegen zu
     * kommen, wird bei einem AVL Baum der folgende Algorithmus angewandt:
     * 
     * b) Der Knoten wird gemäß dem Algorithmus eines binären Suchbaumes
     * eingefügt. Danach wird überprüft, ob die AVL Eigenschaft verletzt wird.
     * Wenn eine Verletzung vorliegt, wird dies per Rotation der Teilbäume
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
        // Verletzung des Kriteriums - Rechter Teilbaum größer. Einmal Links rotieren
        tree.insert(17, null);    example.halt("17");
        // Keine Verletzung
        tree.insert(7,  null);    example.halt("7");
        // Verletzung des Kriteriums - Linker Teilbaum größer. Einmal rechts rotieren(von der sieben aus)
        tree.insert(5,  null);    example.halt("5");
        // Verletzung des Kriteriums - Linker Teilbaum größer. Zweimal links rotieren(von der vierzehn aus)
        // Dann Rechts rotieren
        tree.insert(10, null);    example.halt("10");
        // Verletzung des Kriteriums - Rechter Teilbaum von 15 größer. Einmal rechts rotieren und dann einmal links
        tree.insert(16, null);    example.halt("16");
        example.halt("QUIT");

        // @formatter:on
    }
}
