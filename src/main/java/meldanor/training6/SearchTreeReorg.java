package meldanor.training6;

import aud.BinarySearchTree;
import aud.Vector;
import aud.util.DotViewer;

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
