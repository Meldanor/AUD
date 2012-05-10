package meldanor.training4;

import meldanor.training2.Graphvizable;

/**
 * Simple binary tree.
 * <p>
 * 
 * The {@code BinaryTree} class represents a <em>node</em> and simultaneously
 * its <em>subtree</em>. We do not explclitly distinguish between nodes and a
 * (rooted) tree.
 * <p>
 * 
 * Used for demos and as base class for various binary trees. For every node, we
 * store an uplink to its parent {# getParent}.
 * 
 * @see aud.example.BinaryTreeTraversal
 */
public class BinaryTree<T> implements Graphvizable, GraphvizDecorable {
    BinaryTree<T> parent_ = null;
    BinaryTree<T> left_ = null;
    BinaryTree<T> right_ = null;
    T data_ = null;

    /** create new root node without children */
    // @<binarytree:ctor
    public BinaryTree(T data) {
        data_ = data;
    }
    // @>binarytree:ctor

    /** create new root node with children */
    // @<binarytree:ctor2
    public BinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
        this(data);
        left_ = left;
        right_ = right;
        if (left != null)
            left_.parent_ = this;
        if (right != null)
            right_.parent_ = this;
    }
    // @>binarytree:ctor2

    /**
     * set left subtree
     * 
     * @return previous subtree or {@code null}
     */
    // @<binarytree:setleft
    public BinaryTree<T> setLeft(BinaryTree<T> tree) {
        BinaryTree<T> old = left_;
        left_ = tree;
        if (tree != null)
            tree.parent_ = this;
        if (old != null)
            old.parent_ = null;
        return old;
    }
    // @>binarytree:setleft
    /**
     * set right subtree
     * 
     * @return previous subtree or {@code null}
     */
    public BinaryTree<T> setRight(BinaryTree<T> tree) {
        BinaryTree<T> old = right_;
        right_ = tree;
        if (tree != null)
            tree.parent_ = this;
        if (old != null)
            old.parent_ = null;
        return old;
    }

    /** set node data */
    public void setData(T data) {
        data_ = data;
    }
    /** get node data */
    public T getData() {
        return data_;
    }

    /** get node's parent or {@code null} for root */
    public BinaryTree<T> getParent() {
        return parent_;
    }
    /** get left child or {@code null} */
    public BinaryTree<T> getLeft() {
        return left_;
    }
    /** get right child or {@code null}) */
    public BinaryTree<T> getRight() {
        return right_;
    }

    /** Is @{code this} root? */
    // @<binarytree:isroot
    public boolean isRoot() {
        return getParent() == null;
    }
    // @>binarytree:isroot
    /** Is {@code this} a leaf? */
    // @<binarytree:isleaf
    public boolean isLeaf() {
        return getLeft() == null && getRight() == null;
    }
    // @>binarytree:isleaf

    /** traverse upwards to find root node */
    // @<binarytree:getroot
    public BinaryTree<T> getRoot() {
        BinaryTree<T> node = this;
        while (!node.isRoot())
            node = node.getParent();
        return node;
    }
    // @>binarytree:getroot

    /**
     * Get preorder iterator over nodes in tree .
     * <p>
     * Examples:
     * <p>
     * {@code Iterator<BinaryTree<T> > ii=tree.preorder().iterator();}
     * <p>
     * {@code for (BinaryTree<T> node : tree.preorder()) ... }
     * 
     * @return instance of {@code Iterable}
     * @see BinaryTreeTraversal.PreorderIterator
     */
    public BinaryTreeTraversal<T>.Preorder preorder() {
        return new BinaryTreeTraversal<T>().preorder(this);
    }
    /**
     * Get inorder iterator over nodes in tree .
     * <p>
     * 
     * @return instance of {@code Iterable}
     * @see #preorder
     * @see BinaryTreeTraversal.InorderIterator
     */
    public BinaryTreeTraversal<T>.Inorder inorder() {
        return new BinaryTreeTraversal<T>().inorder(this);
    }
    /**
     * Get postorder iterator over nodes in tree .
     * <p>
     * 
     * @return instance of {@code Iterable}
     * @see #preorder
     * @see BinaryTreeTraversal.PostorderIterator
     */
    public BinaryTreeTraversal<T>.Postorder postorder() {
        return new BinaryTreeTraversal<T>().postorder(this);
    }
    /**
     * Get level-order iterator over nodes in tree .
     * <p>
     * 
     * @return instance of {@code Iterable}
     * @see #preorder
     * @see BinaryTreeTraversal.LevelorderIterator
     */
    public BinaryTreeTraversal<T>.Levelorder levelorder() {
        return new BinaryTreeTraversal<T>().levelorder(this);
    }

    /**
     * Get string presentation of node data.
     * <p>
     * This method only encodes the node's <em>content</em> as string, it does
     * <em>not</em> encode the entire tree! (This makes it easier to, e.g.,
     * print out a {@link Stack} of nodes.)
     * 
     * @return {@link #getData}{@code .toString}
     * @see BinaryTreeTraversal.Traversal#toString
     */
    @Override
    public String toString() {
        return getData() != null ? getData().toString() : "null";
    }

    @Override
    public GraphvizDecorator getDecorator() {
        return null;
    }

    @Override
    public String toDot() {
        String dot = "graph BinaryTree {\n";

        GraphvizDecorator decorator = getDecorator();
        if (decorator != null) {
            String d = decorator.getGlobalStyle();
            if (d != null)
                dot += " " + d + ";\n";
            d = decorator.getGraphDecoration(this);
            if (d != null)
                dot += " graph [" + d + "];\n";
        }

        dot += treeToDot();

        dot += "\n}\n";
        // System.out.println(dot);
        return dot;
    }

    /**
     * Get name for "referencing a node.
     * <p>
     * Unlike in graphs, {@link #getData} can appier multiple times, i.e., nodes
     * may contain the same "data". Using {@code dotRef} prevents GraphViz from
     * eventually misinterpreting the data and drawing a graph with cycles.
     */
    private String dotRef() {
        return getData().toString() + "-" + hashCode();
    }

    /*
     * get text representation of node for {@link #toDot}
     * 
     * @return {@code getData().toString()} as default
     */
    protected String dotLabel() {
        return getData().toString();
    }

    /** recursive, called by {@link #toDot} */
    private String treeToDot() {
        // Currently no "root" or "same rank" tags. Do we require them?

        GraphvizDecorator decorator = getDecorator();
        String dot = " \"" + dotRef() + "\" [label=\"" + dotLabel() + "\",";
        dot += (decorator != null) ? decorator.getFullNodeDecoration(this) : "shape=circle";
        dot += "];\n";

        if (getLeft() != null)
            dot += getLeft().treeToDot();
        else
            dot += dotLeaf("left");

        if (getRight() != null)
            dot += getRight().treeToDot();
        else
            dot += dotLeaf("right");

        dot += "\n";

        if (!isRoot()) { // edge is identified by "lower" node
            dot += " \"" + getParent().dotRef() + "\" -- \"" + dotRef() + "\" ["; // no
                                                                                  // edge
                                                                                  // labels
                                                                                  // currently
            dot += (decorator != null) ? decorator.getFullEdgeDecoration(this) : "";
            dot += "];\n";
        }

        return dot;
    }

    /** draw leaf */
    private String dotLeaf(String side) {
        String dummy = "\"" + dotRef() + "-" + side + "\"";
        String dot = " " + dummy + " [shape=point];\n";
        dot += " \"" + dotRef() + "\" -- " + dummy + " [];\n";
        return dot;
    }

    /** get multiline text visualization */
    public String toText() {
        return toText(0);
    }

    /** recursive, called by {@link toText} */
    private String toText(int level) {
        String text = "";
        text += indent(level) + "+- " + textLabel() + "\n";

        if (getLeft() == null && getRight() == null)
            return text;

        if (getLeft() != null)
            text += getLeft().toText(level + 1);
        else
            text += indent(level + 1) + "+-\n";

        if (getRight() != null)
            text += getRight().toText(level + 1);
        else
            text += indent(level + 1) + "+-\n";

        return text;
    }
    /** get indentation string filled with spaces */
    public static String indent(int level) {
        String spaces = "";
        for (int i = 0; i < level; ++i)
            spaces += "  ";
        return spaces;
    }

    /**
     * Get string representation of data in {@link #toText}
     * 
     * @return {@code getData().toString()} (default implementation)
     */
    protected String textLabel() {
        return getData().toString();
    }

    /** get TikZ code for LaTeX export */
    public String toTikZ() {
        return "\\" + toTikZ(0) + ";\n";
    }
    protected String tikzNodeStyle() {
        return "";
    }
    protected String toTikZ(int level) {
        String spaces = indent(level);
        String tex = spaces + "node " + tikzNodeStyle() + " {" + getData().toString() + "}\n";
        if (getLeft() != null)
            tex += spaces + " child[left] {\n" + getLeft().toTikZ(level + 1) + spaces + " }\n";
        if (getRight() != null)
            tex += spaces + " child[right] {\n" + getRight().toTikZ(level + 1) + spaces + " }\n";

        return tex;
    }

    public static void main(String[] args) {
        BinaryTree<String> a = new BinaryTree<String>("A");
        BinaryTree<String> b = new BinaryTree<String>("B");
        BinaryTree<String> c = new BinaryTree<String>("C");
        BinaryTree<String> d = new BinaryTree<String>("D");

        a.setLeft(b);
        a.setRight(c);
        c.setLeft(d);

        System.out.println(a);
        System.out.println(a.toText());
        System.out.println(a.toTikZ());

        System.out.println("preorder: " + a.preorder().toString());
        System.out.println("inorder: " + a.inorder().toString());
        System.out.println("postorder: " + a.postorder().toString());
        System.out.println("level-order: " + a.levelorder().toString());

        // System.out.println(a.toDot());
    }
}
