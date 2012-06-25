/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training10;

import aud.example.graph.BreadthFirstSearch;
import aud.example.graph.DepthFirstSearch;
import aud.example.graph.MyGraph;
import aud.example.graph.MyNode;
import aud.util.SingleStepper;

/**
 * @author Meldanor
 * 
 */
public class BlaMyGraph {

    public static void main(String[] args) {
        MyGraph t = new MyGraph(false);

        // GENERATE THE NODES FROM 1 TO 8
        MyNode[] nodes = new MyNode[8];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = t.addNode();
            nodes[i].setLabel(Integer.toString(i + 1));
        }

        // 1 to 2 and 2 to 1
        t.addEdge(nodes[0], nodes[1]);
        // 1 to 3 and 3 to 1
        t.addEdge(nodes[0], nodes[2]);
        // 1 to 6 and 6 to 1
        t.addEdge(nodes[0], nodes[5]);
        // 1 to 7 and 7 to 1
        t.addEdge(nodes[0], nodes[6]);
        // 1 to 8 and 8 to 1
        t.addEdge(nodes[0], nodes[7]);

        // 2 to 8 and 8 to 2
        t.addEdge(nodes[1], nodes[7]);

        // 3 to 8 and 8 to 3
        t.addEdge(nodes[2], nodes[7]);

        // 4 to 5 and 5 to 4
        t.addEdge(nodes[3], nodes[4]);
        // 4 to 6 and 6 to 4
        t.addEdge(nodes[3], nodes[5]);

        // 5 to 6 and 6 to 5
        t.addEdge(nodes[4], nodes[5]);
        // 5 to 7 and 7 to 5
        t.addEdge(nodes[4], nodes[6]);
        // 5 to 8 and 8 to 5
        t.addEdge(nodes[4], nodes[7]);

        System.out.println("Breitensuche");
        BreadthFirstSearch bs = new BreadthFirstSearch(t);
        bs.singlestepper = new SingleStepper((String) null);
        bs.start(nodes[7]);

        System.out.println("Tiefensuche");
        DepthFirstSearch ds = new DepthFirstSearch(t);
        ds.singlestepper = new SingleStepper((String) null);
        ds.start(nodes[7]);
    }
}
