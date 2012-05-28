package meldanor.training6;

public class SimpleTree<T extends Comparable<T>> {

    public class Node {

        private SimpleTree<T> parent;

        private SimpleTree<T> leftChild = new SimpleTree<T>();
        private SimpleTree<T> rightChild = new SimpleTree<T>();

        private T data;

        public Node(T data) {
            this.data = data;
        }

        public Node(SimpleTree<T> parent, T data) {
            this.parent = parent;
            this.data = data;
        }
    }

    private Node root = null;

    public SimpleTree() {
        // EMPTY
    }

    // NEED FOR INSERT
    private SimpleTree(SimpleTree<T> parent, T data) {
        root = new Node(parent, data);
    }

    public void insert(T obj) {

        if (root == null)
            root = new Node(obj);
        else {
            SimpleTree<T> current = this;
            // TERMINATE
            while (!current.root.data.equals(obj)) {
                // GO RIGHT TREE
                if (current.root.data.compareTo(obj) < 0) {
                    if (current.root.rightChild.isEmpty()) {
                        current.root.rightChild = new SimpleTree<T>(current, obj);
                        break;
                    } else
                        current = current.root.rightChild;

                }
                // GO LEFT TREE
                else if (current.root.data.compareTo(obj) > 0) {
                    if (current.root.leftChild.isEmpty()) {
                        current.root.leftChild = new SimpleTree<T>(current, obj);
                        break;
                    } else
                        current = current.root.leftChild;

                }
            }
        }
    }

    public boolean search(T obj) {
        if (root == null) {
            return false;
        } else {
            if (root.data.equals(obj))
                return true;
            if (root.data.compareTo(obj) < 0)
                return root.rightChild.search(obj);
            else
                return root.leftChild.search(obj);
        }

    }

    public boolean isEmpty() {
        return root == null;
    }

    public String toString() {
        if (isEmpty())
            return "";

        StringBuilder s = new StringBuilder();

        if (this.root.leftChild != null)
            s.append(this.root.leftChild.toString());

        s.append(this.root.data);
        s.append(" ");
        if (this.root.rightChild != null)
            s.append(this.root.rightChild.toString());

        return s.toString();
    }

    public static void main(String[] args) {
        SimpleTree<Integer> myTree = new SimpleTree<Integer>();

        myTree.insert(5);
        myTree.insert(2);
        myTree.insert(1);
        myTree.insert(3);

        myTree.insert(8);
        myTree.insert(6);
        myTree.insert(9);
        myTree.insert(7);

        myTree.insert(9);

        System.out.println(myTree);

        System.out.println(myTree.search(2));
        System.out.println(myTree.search(-2));
    }
}
