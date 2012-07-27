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

import java.util.ArrayList;

import aud.example.graph.DijkstraShortestPaths;
import aud.example.graph.MyEdge;
import aud.example.graph.MyGraph;
import aud.example.graph.MyNode;
import aud.example.graph.TraversalExample;

/**
 * <pre>
 * Erläutern Sie den in der Vorlesung vorgestellten Dijkstra-Algorithmus zur Suche kürzester Wege an diesem Beispiel. Geben Sie dabei alle Schritte an, wenn Sie bei Knoten 0 beginnen.
 * 
 *     Konstruieren Sie sich einen (möglichst einfachen) gewichteten Graphen, bei dem sich für mindestens einen Knoten während der Anwendung des Algorithmus von Dijkstra die Approximation für die Distanz mindestens zweimal ändert.
 *     Können Sie Ihr Beispiel so verallgemeinern, dass sich für ein beliebig vorgegebenes n (mit n > 2) für mindestens einen Knoten während der Anwendung des Algorithmus von Dijkstra die Approximation für die Distanz mindestens n-mal ändert?
 *     Geben Sie einen (möglichst einfachen) Graphen an, mit dem Sie illustrieren, dass der Algorithmus von Dijkstra im Falle negativer Kantengewichte im allgemeinen nicht korrekt arbeitet.
 * </pre>
 */
public class Dijkstra {

    public static void main(String[] args) {
        MyGraph graph = new MyGraph(false);
        ArrayList<MyNode> nodes = new ArrayList<MyNode>();

        for (int i = 0; i < 6; ++i)
            nodes.add(graph.addNode());

        ArrayList<MyEdge> edges = new ArrayList<MyEdge>();
        MyEdge e; // temp

        // LINK NODES AND WEIGHT THEM
        // 0 to 1
        e = graph.addEdge(nodes.get(0), nodes.get(1));
        e.setWeight(2);
        edges.add(e);
        // 0 to 2
        e = graph.addEdge(nodes.get(0), nodes.get(2));
        e.setWeight(5);
        edges.add(e);
        // 0 to 3
        e = graph.addEdge(nodes.get(0), nodes.get(3));
        e.setWeight(3);
        edges.add(e);

        // 1 to 5
        e = graph.addEdge(nodes.get(1), nodes.get(5));
        e.setWeight(4);
        edges.add(e);

        // 2 to 3
        e = graph.addEdge(nodes.get(2), nodes.get(3));
        e.setWeight(1);
        edges.add(e);
        // 2 to 4
        e = graph.addEdge(nodes.get(2), nodes.get(4));
        e.setWeight(7);
        edges.add(e);
        // 2 to 5
        e = graph.addEdge(nodes.get(2), nodes.get(5));
        e.setWeight(8);
        edges.add(e);

        // 3 to 5
        e = graph.addEdge(nodes.get(3), nodes.get(5));
        e.setWeight(6);
        edges.add(e);

        // 4 to 5
        e = graph.addEdge(nodes.get(4), nodes.get(5));
        e.setWeight(9);
        edges.add(e);

        // visit 0 [p=null, d=0.0]
        // visit 1 [p=0, d=2.0]
        // visit 3 [p=0, d=3.0]
        // visit 2 [p=3, d=4.0]
        // visit 5 [p=1, d=6.0]
        // visit 4 [p=2, d=11.0]

        // VISUALIZE
        TraversalExample ts = new TraversalExample();

        DijkstraShortestPaths ds = new DijkstraShortestPaths(graph);
        ts.traversal = ds;
        ds.singlestepper = ts;
        ds.nsteps = 1;
        ds.verbose = 0;
        ds.start(nodes.get(0));

    }
}
