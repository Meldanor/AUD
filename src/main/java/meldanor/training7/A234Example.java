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
 * <pre>
 *     Was ist ein 2-3-4 Baum?
 *     Zeigen Sie anhand folgenden Beispiels, dass die Reihenfolge des Einfügens von Werten für die Struktur des 2-3-4 Baumes entscheidend ist:
 *         Fügen Sie in folgender Reihenfolge die Zahlen 5, 1, 19, 25, 17, 21, 9, 15 und 14 in einen 2-3-4 Baum ein.
 *         Bauen Sie nun in der veränderten Reihenfolge 25, 9, 14, 1, 17, 21, 15, 5 und 19 einen weiteren 2-3-4 Baum auf.
 *         Kontrollieren Sie Ihre Schritte mit dem Dotviewer und SingleStepper.
 * </pre>
 */
public class A234Example extends SingleStepper {
    /*****************
     * a) Jeder Knoten hat 2, 3 oder 4 Kindknoten. Jeder Knoten hält jeweils
     * eine sortierte Liste von 1, 2 oder 3 Keys. Wenn ein Knoten mehr als 3
     * Keys halten muss, wird ein Split ausgeführt. Das mittlere Element des
     * aufzusplittenden Knoten wird der Vater Knoten der beiden neuen Knoten.
     * 
     * 
     * 
     * b) Bei der zweiten Reihenfolge sind nur 2 Splits vonnöten, bei der ersten
     * Reihenfolge 4 Splits. Auch sind nicht soviele Kindknoten beim 2. nötig
     * wie beim 1. Algorithmus am Beispiel als Kommentare im Quellcode
     *********************/

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
        // In Wurzel
        tree.insert(5);     example.halt("5");
        // in Wurzel, vor 5
        tree.insert(1);     example.halt("1");
        // in Wurzel, nach 5
        tree.insert(19);    example.halt("19");
        // Split -> 5 Wird wurzel, 1 linker teilbaum, 19 und 25 rechter
        tree.insert(25);    example.halt("25");
        // bei 19 und 25 einfügen
        tree.insert(17);    example.halt("17");
        // Split bei 17, 19, 21 und 25 -> 19 in die wurzel ziehen, 17 wird 2. teilbaum  von wurzel und 21 und 25 rechter
        tree.insert(21);    example.halt("21");
        // 9 zwischen 5 und 19 und mittlerer knoten existiert -> bei 17 einfügen
        tree.insert(9);     example.halt("9");
        // 15 zwischen 5 und 19 und mittlerer knoten existiert -> bei 9,17 einfügen
        tree.insert(15);    example.halt("15");  
        // 14 zwischen 5 und 19 und mittlerer knoten existiert -> bei 9,15,17 einfügen
        // SPLIT -> 9,14,15,17. 9 wird zweiter Kindknoten der Wurzel, 14 in die Wurzel und 15 und 17 bleiben
        tree.insert(14);    example.halt("14");

        example.halt("QUIT");

        // @formatter:on

        A234Tree<Integer> tree2 = new A234Tree<Integer>();
        A234Example example2 = new A234Example(tree2);

        // @formatter:off

        // 25 in Wurzel
        tree2.insert(25);   example2.halt("25");
        // 9 in Wurzel, sortieren
        tree2.insert(9);    example2.halt("9");
        // 14 in Wurzel, sortieren
        tree2.insert(14);   example2.halt("14");
        // 1 in Wurzel -> Split bei 1,9,14,25
        // 9 wird wurzel , 1 ist linker teilbaum, 14 und 25 der rechte knoten
        tree2.insert(1);    example2.halt("1");
        // 17 größer 9 -> bei 14 und 25 einfügen
        tree2.insert(17);   example2.halt("17");
        // 21 größer 9 -> bei 14, 17 und 25 einfügen -> Split bei 14, 17, 21 , 25
        // 17 in die Wurzel, 14 wird 2. linker kindknoten von wurzel und 21 und 25 bleiben
        tree2.insert(21);   example2.halt("21");
        // 15 zwischen 9 und 17 -> in 2. linken kindknoten einfügen (14)
        tree2.insert(15);   example2.halt("15");
        // 5 kleiner 9 -> bei 1. linken kindknoten einfügen (1)
        tree2.insert(5);    example2.halt("5");
        // 19 größer 17 -> in 3. linken kindknoten einfügen
        tree2.insert(19);   example2.halt("19");

        example2.halt("QUIT");

        // @formatter:on
    }

}
