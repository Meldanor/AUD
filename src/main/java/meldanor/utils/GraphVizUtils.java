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

    // *******************************
    // ***** SINGLE LINKED LIST ******
    // *******************************

    /**
     * Create a graph of a single linked list. The output format is PNG and the
     * target file is "out.png". <br>
     * The default direction of the graph is TOP_BOTTOM {@link Direction}
     * 
     * @param list
     *            The iterable list
     */
    public static void visualizeSingleLinkedList(Iterable<?> list) {
        visualizeSingleLinkedList(list, new File("target/out.png"), Format.PNG, Direction.TOP_BOTTOM);
    }

    /**
     * Create a graph of a single linked list. The output format depends on the
     * parameter. <br>
     * The default direction of the graph is TOP_BOTTOM {@link Direction}
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
        visualizeSingleLinkedList(list, new File(targetName), formatType, Direction.TOP_BOTTOM);
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
     * @param dir
     *            The direction of the graph. See {@link Direction}
     */
    public static void visualizeSingleLinkedList(Iterable<?> list, String targetName, Format formatType, Direction dir) {
        // RENAME FILE IF ENDING DIFFER FROM TYPE ENDING
        if (!targetName.endsWith(formatType.getType()))
            targetName = targetName.concat(".").concat(formatType.getType());

        // CREATE GRAPH
        visualizeSingleLinkedList(list, new File(targetName), formatType, dir);
    }

    /**
     * Create a graph of a single linked list. The output format depends on the
     * parameter. <br>
     * The default direction of the graph is TOP_BOTTOM {@link Direction}
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
        visualizeSingleLinkedList(list, target, formatType, Direction.TOP_BOTTOM);
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
     * @param dir
     *            The direction of the graph. See {@link Direction}
     */
    public static void visualizeSingleLinkedList(Iterable<?> list, File target, Format formatType, Direction dir) {
        GraphViz gv = startGraph(dir);

        Iterator<?> iter = list.iterator();
        // LIST IS NOT EMPTY
        if (iter.hasNext()) {

            // CREATE NODES OF LIST
            Object t1 = null;
            Object t2 = iter.next();
            while (iter.hasNext()) {
                t1 = iter.next();
                gv.addln(t2.toString() + " -> " + t1.toString() + "[label=\"next\"];");
                t2 = t1;
            }
        }

        finishGraph(target, formatType, gv);
    }

    // *******************************
    // ***** DOUBLE LINKED LIST ******
    // *******************************

    /**
     * Create a graph of a double linked list. The output format is PNG and the
     * target file is "out.png"<br>
     * The default direction of the graph is TOP_BOTTOM {@link Direction}
     * 
     * @param list
     *            The iterable list
     */
    public static void visualizeDoubleLinkedList(Iterable<?> list) {
        visualizeDoubleLinkedList(list, new File("target/out.png"), Format.PNG, Direction.TOP_BOTTOM);
    }

    /**
     * Create a graph of a double linked list. The output format depends on the
     * parameter.<br>
     * The default direction of the graph is TOP_BOTTOM {@link Direction}
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
    public static void visualizeDoubleLinkedList(Iterable<?> list, String targetName, Format formatType) {
        // RENAME FILE IF ENDING DIFFER FROM TYPE ENDING
        if (!targetName.endsWith(formatType.getType()))
            targetName = targetName.concat(".").concat(formatType.getType());

        // CREATE GRAPH
        visualizeDoubleLinkedList(list, new File(targetName), formatType, Direction.TOP_BOTTOM);
    }

    /**
     * Create a graph of a double linked list. The output format depends on the
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
     * @param dir
     *            The direction of the graph. See {@link Direction}
     */
    public static void visualizeDoubleLinkedList(Iterable<?> list, String targetName, Format formatType, Direction dir) {
        // RENAME FILE IF ENDING DIFFER FROM TYPE ENDING
        if (!targetName.endsWith(formatType.getType()))
            targetName = targetName.concat(".").concat(formatType.getType());

        // CREATE GRAPH
        visualizeDoubleLinkedList(list, new File(targetName), formatType, dir);
    }

    /**
     * Create a graph of a double linked list. The output format depends on the
     * parameter.<br>
     * The default direction of the graph is TOP_BOTTOM {@link Direction}
     * 
     * @param list
     *            The iterable list
     * @param target
     *            The outputFile. If the ending differs from the ending of the
     *            formatType, the targetName will be renamed
     * @param formatType
     *            The output file format. See {@link Format}
     */
    public static void visualizeDoubleLinkedList(Iterable<?> list, File target, Format formatType) {
        visualizeDoubleLinkedList(list, target, formatType, Direction.TOP_BOTTOM);
    }

    /**
     * Create a graph of a double linked list. The output format depends on the
     * parameter.
     * 
     * @param list
     *            The iterable list
     * @param target
     *            The outputFile. If the ending differs from the ending of the
     *            formatType, the targetName will be renamed
     * @param formatType
     *            The output file format. See {@link Format}
     * @param dir
     *            The direction of the graph. See {@link Direction}
     */
    public static void visualizeDoubleLinkedList(Iterable<?> list, File target, Format formatType, Direction dir) {
        GraphViz gv = startGraph(dir);

        Iterator<?> iter = list.iterator();
        // LIST IS NOT EMPTY
        if (iter.hasNext()) {

            // CREATE NODES OF LIST
            Object t1 = null;
            Object t2 = iter.next();

            while (iter.hasNext()) {
                t1 = iter.next();
                gv.addln(t2.toString() + " -> " + t1.toString() + "[label=\"next\"];");
                gv.addln(t1.toString() + " -> " + t2.toString() + "[label=\"prev\"];");
                t2 = t1;
            }
        }

        finishGraph(target, formatType, gv);
    }

    // *******************************
    // ********* ARRAY LIST **********
    // *******************************

    /**
     * Create a graph of a array list. The output format depends on the target
     * file is "out.png"<br>
     * The default direction of the graph is TOP_BOTTOM {@link Direction}
     * 
     * @param list
     *            The iterable list
     */
    public static void visualizeArrayList(Iterable<?> list) {
        visualizeArrayList(list, new File("target/out.png"), Format.PNG, Direction.TOP_BOTTOM);
    }

    /**
     * Create a graph of a array list. The output format depends on the
     * parameter.<br>
     * The default direction of the graph is TOP_BOTTOM {@link Direction}
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
    public static void visualizeArrayList(Iterable<?> list, String targetName, Format formatType) {
        // RENAME FILE IF ENDING DIFFER FROM TYPE ENDING
        if (!targetName.endsWith(formatType.getType()))
            targetName = targetName.concat(".").concat(formatType.getType());

        // CREATE GRAPH
        visualizeArrayList(list, new File(targetName), formatType, Direction.TOP_BOTTOM);
    }

    /**
     * Create a graph of a array list. The output format depends on the
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
     * @param dir
     *            The direction of the graph. See {@link Direction}
     */
    public static void visualizeArrayList(Iterable<?> list, String targetName, Format formatType, Direction dir) {
        // RENAME FILE IF ENDING DIFFER FROM TYPE ENDING
        if (!targetName.endsWith(formatType.getType()))
            targetName = targetName.concat(".").concat(formatType.getType());

        // CREATE GRAPH
        visualizeArrayList(list, new File(targetName), formatType, dir);
    }

    /**
     * Create a graph of a array list. The output format depends on the
     * parameter.<br>
     * The default direction of the graph is TOP_BOTTOM {@link Direction}
     * 
     * @param list
     *            The iterable list
     * @param target
     *            The outputFile. If the ending differs from the ending of the
     *            formatType, the targetName will be renamed
     * @param formatType
     *            The output file format. See {@link Format}
     */
    public static void visualizeArrayList(Iterable<?> list, File target, Format formatType) {
        visualizeArrayList(list, target, formatType, Direction.TOP_BOTTOM);
    }

    /**
     * Create a graph of a array list. The output format depends on the
     * parameter.
     * 
     * @param list
     *            The iterable list
     * @param target
     *            The outputFile. If the ending differs from the ending of the
     *            formatType, the targetName will be renamed
     * @param formatType
     *            The output file format. See {@link Format}
     * @param dir
     *            The direction of the graph. See {@link Direction}
     */
    public static void visualizeArrayList(Iterable<?> list, File target, Format formatType, Direction dir) {
        GraphViz gv = startGraph(dir);

        gv.addln("node [shape=record];");
        StringBuilder sBuilder = new StringBuilder(512);
        sBuilder.append("struct1 [label=\"");

        Iterator<?> iter = list.iterator();

        while (iter.hasNext()) {
            sBuilder.append(iter.next());
            if (iter.hasNext())
                sBuilder.append('|');
        }
        sBuilder.append("\"];");
        gv.addln(sBuilder.toString());

        finishGraph(target, formatType, gv);
    }

    // *******************************
    // ***** COMMON HELPER FUNCS *****
    // *******************************

    private static void finishGraph(File target, Format formatType, GraphViz gv) {
        // FINISH GRAPH
        gv.addln(gv.end_graph());

        // RENAME FILE IF ENDING DIFFER FROM TYPE ENDING
        if (!target.getName().endsWith(formatType.getType()))
            target = new File(target.getName().concat(".").concat(formatType.getType()));

        // WRITE FILE
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), formatType.getType()), target);
    }

    private static GraphViz startGraph(Direction dir) {
        // CREATE GRAPH AND HEAD
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        gv.addln("rankdir=" + dir.getDirTag());
        return gv;
    }
}
