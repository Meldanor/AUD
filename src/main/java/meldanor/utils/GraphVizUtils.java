/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.utils;

import java.io.File;
import java.util.Iterator;

/**
 * @author Meldanor
 * 
 */
public class GraphVizUtils {

    /**
     * Create a graph of a single linked list. The output format is PNG and the
     * target file is "out.png"
     * 
     * @param list
     *            The iterable list
     */
    public static void visualizeSingleLinkedList(Iterable<?> list) {
        visualizeSingleLinkedList(list, new File("out.png"), Format.PNG);
    }

    /**
     * Create a graph of a single linked list. The output format depends on the
     * parameter.
     * 
     * @param list
     *            The iterable list
     * @param targetName
     *            The name of the file as relative path. If the ending differs
     *            from the ending of the formatType, the targetName will be
     *            renamed
     * @param formatType
     *            The output file format. See {@link Format}
     */
    public static void visualizeSingleLinkedList(Iterable<?> list, String targetName, Format formatType) {
        // RENAME FILE IF ENDING DIFFER FROM TYPE ENDING
        if (!targetName.endsWith(formatType.getType()))
            targetName = targetName.concat(".").concat(formatType.getType());

        // CREATE GRAPH
        visualizeSingleLinkedList(list, new File(targetName), formatType);
    }

    /**
     * Create a graph of a single linked list. The output format depends on the
     * parameter.
     * 
     * @param list
     *            The iterable list
     * @param target
     *            The outputFile. If the ending differs from the ending of the
     *            formatType, the targetName will be renamed
     * @param formatType
     *            The output file format. See {@link Format}
     */
    public static void visualizeSingleLinkedList(Iterable<?> list, File target, Format formatType) {
        // CREATE GRAPH AND HEAD
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());

        Iterator<?> iter = list.iterator();
        // LIST IS NOT EMPTY
        if (iter.hasNext()) {

            // CREATE NODES OF LIST
            Object t1 = null;
            Object t2 = iter.next();
            while (iter.hasNext()) {
                t1 = iter.next();
                gv.addln(t2.toString() + " -> " + t1.toString() + ";");
                t2 = t1;
            }
        }

        // FINISH GRAPH
        gv.addln(gv.end_graph());

        // RENAME FILE IF ENDING DIFFER FROM TYPE ENDING
        if (!target.getName().endsWith(formatType.getType()))
            target = new File(target.getName().concat(".").concat(formatType.getType()));

        // WRITE FILE
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), formatType.getType()), target);
    }
}
