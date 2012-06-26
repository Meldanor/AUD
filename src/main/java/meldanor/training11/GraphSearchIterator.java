/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training11;

/**
 * @author Meldanor
 *
 */
import java.util.Iterator;

import aud.Stack;
import aud.example.graph.MyEdge;
import aud.example.graph.MyGraph;
import aud.example.graph.MyNode;

public class GraphSearchIterator extends MyGraph implements Iterable<MyNode> {

    GraphSearchIterator(boolean directed) {
        super(directed);
    }
    public class DFSIterator implements java.util.Iterator<MyNode> {

        private Stack<MyNode> st;

        public DFSIterator(MyNode start) {
            st = new Stack<MyNode>();
            dfs(start);
        }

        private void dfs(MyNode node) {
            Stack<MyNode> temp = new Stack<MyNode>();
            temp.push(node);
            mark(node);
            MyNode t = null;
            MyNode t2 = null;
            while (!temp.is_empty()) {
                t = temp.pop();
                mark(t);
                st.push(t);
                for (MyEdge edge : getOutEdges(t)) {
                    t2 = (MyNode) edge.destination();
                    if (t2.ord != 1)
                        temp.push(t2);
                }
            }
        }

        private void mark(MyNode node) {
            node.ord = 1;
        }

        public boolean hasNext() {
            return !st.is_empty();
        }

        public MyNode next() {
            return st.pop();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public DFSIterator iterator() {
        return new DFSIterator(getSomeNode());
    }

    public static void main(String args[]) {
        GraphSearchIterator g = new GraphSearchIterator(false);

        // GENERATE THE NODES FROM 1 TO 8
        MyNode[] nodes = new MyNode[8];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = g.addNode();
            nodes[i].setLabel((i + 1) + "");
        }

        // 1 to 2 and 2 to 1
        g.addEdge(nodes[0], nodes[1]);
        // 1 to 3 and 3 to 1
        g.addEdge(nodes[0], nodes[2]);
        // 1 to 6 and 6 to 1
        g.addEdge(nodes[0], nodes[5]);
        // 1 to 7 and 7 to 1
        g.addEdge(nodes[0], nodes[6]);
        // 1 to 8 and 8 to 1
        g.addEdge(nodes[0], nodes[7]);

        // 2 to 8 and 8 to 2
        g.addEdge(nodes[1], nodes[7]);

        // 3 to 8 and 8 to 3
        g.addEdge(nodes[2], nodes[7]);

        // 4 to 5 and 5 to 4
        g.addEdge(nodes[3], nodes[4]);
        // 4 to 6 and 6 to 4
        g.addEdge(nodes[3], nodes[5]);

        // 5 to 6 and 6 to 5
        g.addEdge(nodes[4], nodes[5]);
        // 5 to 7 and 7 to 5
        g.addEdge(nodes[4], nodes[6]);
        // 5 to 8 and 8 to 5
        g.addEdge(nodes[4], nodes[7]);

        Iterator<MyEdge> te = g.getEdgeIterator();
        while(te.hasNext())
            System.out.println(te.next());

//        DotViewer.displayWindow(g, "String");

        for (MyNode el : g)
            System.out.print(el.getLabel() + " ");

    }
}
