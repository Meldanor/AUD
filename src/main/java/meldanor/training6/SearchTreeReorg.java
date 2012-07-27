/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training6;

import aud.BinarySearchTree;
import aud.Vector;
import aud.util.DotViewer;

/**
 * <pre>
 *  Gegeben sei folgender binärer Suchbaum
 * Bauen Sie einen neuen binären Suchbaum wie folgt auf:
 * 
 *     Lesen Sie den gegebenen binären Baum nach der Strategie inorder in einen Vector aus.
 *     Fügen Sie die einzelnen Elemente nach folgender Strategie in einen neuen binären Suchbaum ein:
 *         Wählen Sie das mittlere (abgerundet) Element aus und fügen Sie es als Wurzel des neuen Suchbaumes ein.
 *         Verfahren Sie entsprechend mit dem verbleibenden linken und rechten Teil, indem jeweils die mittleren Elemente als Nachfolger eingefügt werden.
 * 
 *     Veranschaulichen Sie die Arbeitsweise dieses Vorgehens am obigen Beispiel. Wie sieht der neue Baum aus?
 *     Was können Sie allgemein über die Eigenschaften (ausgeglichen, voll) des neu aufgebauten Suchbaums sagen?
 *     Schreiben Sie eine Klasse SearchTreeReorg als Erweiterung der Klasse BinarySearchTree mit der Methode
 * 
 *     public SearchTreeReorg reorganize(),
 * 
 *     die den obigen Algorithmus realisiert.
 * 
 * Hinweise:
 * 
 *     Alle benötigten Klassen finden Sie unter den "Folien und Materialien" auf der Seite "Code/Beispiele".
 *     Für den Backendtest dürfenrf diese Klasse nicht importiert werden.
 *     Bei dem Backendtest müssen Sie auf die Visualisierung mit DotViewer und SingleStepper verzichten.
 * </pre>
 */
public class SearchTreeReorg<Key extends Comparable<Key>, Value> extends BinarySearchTree<Key, Value> {

    public SearchTreeReorg() {
        super();
    }

    public String levelOrder() {
        // do not change because of backend-control
        return head_.getRight().levelorder().toString();
    }

    public SearchTreeReorg<Key, Value> reorganize() {

        // THE TARGET TREEE
        SearchTreeReorg<Key, Value> resortTree = new SearchTreeReorg<Key, Value>();

        // GENERATE A SORT LIST
        Vector<Cursor> vector = new Vector<Cursor>();
        for (Cursor cursor : this)
            vector.push_back(cursor);

        // REORDER THE TREE
        sortIn(vector, resortTree, 0, vector.size() - 1);

        return resortTree;
    }

    private void sortIn(Vector<Cursor> vec, SearchTreeReorg<Key, Value> resortTree, int left, int right) {

        if (left <= right) {
            int mid = (left + right) / 2;
            // GET THE ELEMENT AT THE MIDDLE
            Cursor midVal = vec.at(mid);

            // INSERT THE MID ELEMENT IN THE TREE
            resortTree.insert(midVal.getKey(), midVal.getValue());

            // HANDLE LEFT SIDE
            sortIn(vec, resortTree, 0, mid - 1);

            // HANDLE RIGHT SIDE
            sortIn(vec, resortTree, mid + 1, right);
        }

    }

    public static void main(String[] args) {

        SearchTreeReorg<Integer, String> tree = new SearchTreeReorg<Integer, String>();
        tree.insert(20, null);
        tree.insert(5, null);
        tree.insert(11, null);
        tree.insert(10, null);
        tree.insert(14, null);
        tree.insert(17, null);
        tree.insert(25, null);
        tree.insert(22, null);
        tree.insert(27, null);
        tree.insert(30, null);
        tree.insert(33, null);

        DotViewer.displayWindow(tree, "Unsortiert");
        tree = tree.reorganize();
        DotViewer.displayWindow(tree, "Sortiert");
    }
}
