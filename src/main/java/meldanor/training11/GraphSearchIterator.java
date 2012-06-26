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
import java.util.ArrayList;

import aud.Queue;
import aud.Stack;
import aud.example.graph.MyEdge;
import aud.example.graph.MyGraph;
import aud.example.graph.MyNode;

public class GraphSearchIterator extends MyGraph implements Iterable<MyNode> {

    GraphSearchIterator(boolean directed) {
        super(directed);
    }

    public class DFSIterator implements java.util.Iterator<MyNode> {

        private Queue<MyNode> st = new Queue<MyNode>();

        public DFSIterator(MyNode start) {
            dfs(start);
        }

        private void dfs(MyNode s0) {
            Stack<MyNode> open = new Stack<MyNode>();
            open.push(s0);
            mark(s0);
            while (!open.is_empty()) {
                MyNode s = open.pop();
                mark(s);
                visit(s);
                for (MyEdge e : getOutEdges(s)) {
                    MyNode t = (MyNode) e.destination();

                    MyNode t2 = (MyNode) e.source();
                    if (!isMarked(t))
                        open.push(t);
                    else if (!isMarked(t2))
                        open.push(t2);
                }
            }
        }

        private void mark(MyNode node) {
            node.ord = 1;
        }

        private boolean isMarked(MyNode node) {
            return node.ord == 1;
        }

        private void visit(MyNode node) {
            st.enqueue(node);
        }

        public boolean hasNext() {
            return !st.is_empty();
        }

        public MyNode next() {
            return st.dequeue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private MyNode start;

    public void setStart(MyNode start) {
        this.start = start;
    }

    public DFSIterator iterator() {
        return new DFSIterator(start != null ? start : getSomeNode());
    }

    public static void main(String args[]) {
        GraphSearchIterator g = new GraphSearchIterator(false);

        // GENERATE THE NODES FROM 1 TO 8
        MyNode[] nodes = new MyNode[8];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = g.addNode();
            nodes[i].setLabel((i + 1) + "");
        }

        ArrayList<MyEdge> edges = new ArrayList<MyEdge>();

        // 1 to 2 and 2 to 1
        edges.add(g.addEdge(nodes[0], nodes[1]));
        // 1 to 3 and 3 to 1
        edges.add(g.addEdge(nodes[0], nodes[2]));
        // 1 to 6 and 6 to 1
        edges.add(g.addEdge(nodes[0], nodes[5]));
        // 1 to 7 and 7 to 1
        edges.add(g.addEdge(nodes[0], nodes[6]));
        // 1 to 8 and 8 to 1
        edges.add(g.addEdge(nodes[0], nodes[7]));

        // 2 to 8 and 8 to 2
        edges.add(g.addEdge(nodes[1], nodes[7]));

        // 3 to 8 and 8 to 3
        edges.add(g.addEdge(nodes[2], nodes[7]));

        // 4 to 5 and 5 to 4
        edges.add(g.addEdge(nodes[3], nodes[4]));
        // 4 to 6 and 6 to 4
        edges.add(g.addEdge(nodes[3], nodes[5]));

        // 5 to 6 and 6 to 5
        edges.add(g.addEdge(nodes[4], nodes[5]));
        // 5 to 7 and 7 to 5
        edges.add(g.addEdge(nodes[4], nodes[6]));
        // 5 to 8 and 8 to 5
        edges.add(g.addEdge(nodes[4], nodes[7]));

//        System.out.println(edges);
//
//        ArrayList<MyEdge> edges2 = new ArrayList<MyEdge>();
//        Iterator<MyEdge> iter = g.getEdgeIterator();
//        while (iter.hasNext())
//            edges2.add(iter.next());
//
//        Collections.sort(edges2);
//
//        System.out.println(edges2);

        g.setStart(nodes[0]);

        for (MyNode el : g)
            System.out.print(el.getLabel() + " ");
//
//        System.out.println("Tiefensuche");
//        DepthFirstSearch ds = new DepthFirstSearch(g);
//        ds.singlestepper = new SingleStepper((String) null);
//        ds.start(nodes[0]);

    }
}
