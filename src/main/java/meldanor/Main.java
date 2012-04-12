/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import meldanor.other.GraphViz;

/**
 * @author Meldanor
 * 
 */
public class Main {

    public static void main(String[] args) {
        test();
    }
    private static void test() {
        LinkedList<String> test = new LinkedList<String>();
        test.add("Kilian");
        test.add("Gaertner");
        test.add("Cool");

        // CREATE GRAPH
        GraphViz gv = new GraphViz();
        // START GRAPH
        gv.addln(gv.start_graph());

        // ADD NEW NODES
        gv.addln(test.get(0) + " -> " + test.get(1) + ";");
        gv.addln(test.get(1) + " -> " + test.get(2) + ";");

        // END GRAPH
        gv.addln(gv.end_graph());

        // PRINT OUT STRUCTURE
        System.out.println(gv.getDotSource());

        // SAVE FILE FROM TYPE PNG
        String type = "png";
        File out = new File("out." + type);

        // CREATE IMAGE
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    }

}
