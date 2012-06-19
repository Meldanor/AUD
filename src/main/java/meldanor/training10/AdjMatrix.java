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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Meldanor
 * 
 */
public class AdjMatrix {

    public static int inDegree(int k, int[][] m) {
        // GET K-COLUMN
        List<Integer> col = getColumn(k, m);
        int counter = 0;
        // COUNT VALUES NOT EQUALS ZERO
        for (int i : col) {
            if (i != 0)
                ++counter;
        }
        return counter;
    }

    public static int outDegree(int k, int[][] m) {
        // GET K-ROW
        List<Integer> col = getRow(k, m);
        int counter = 0;
        // COUNT VALUES NOT EQUALS ZERO
        for (int i : col) {
            if (i != 0)
                ++counter;
        }
        return counter;
    }

    // RETURN THE ROW AS A LIST
    private static List<Integer> getRow(int rowIndex, int[][] m) {
        int[] rowArray = m[rowIndex];
        List<Integer> row = new ArrayList<Integer>(rowArray.length);
        for (int i : rowArray)
            row.add(i);

        return row;
    }

    // RETURN THE COLUMN AS A LIST
    private static List<Integer> getColumn(int colIndex, int[][] m) {
        List<Integer> col = new ArrayList<Integer>(m[0].length);
        for (int i = 0; i < m.length; ++i) {
            col.add(m[i][colIndex]);
        }

        return col;
    }

    public static List<Integer> adjacent(int k, int[][] m) {
        List<Integer> neighbors = new ArrayList<Integer>();
        List<Integer> row = getRow(k, m);
        for (int i = 0; i < row.size(); ++i) {
            if (row.get(i) != 0)
                neighbors.add(i);

        }

        return neighbors;
    }

    public static boolean hasTriangle(int[][] m) {
        List<Integer> neighbors;
        List<Integer> neighbors2;
        // ITERATE OVER ALL NODES
        for (int i = 0; i < m.length; ++i) {
            // GET I-NODE NEIGHBORS
            neighbors = adjacent(i, m);
            // ITERATE OVER ALL I-NODES
            for (int j = 0; j < neighbors.size(); ++j) {
                // GET J-NODE NEIGHBORS(J NODE IS CONNECTED TO I-NODE)
                neighbors2 = adjacent(neighbors.get(j), m);
                // ITERATE OVER ALL J-NODES
                for (int k = 0; k < neighbors2.size(); ++k) {
                    // CONTAINS THE K-NODES(CONNECTED TO J-NODE) THE CURRENT
                    // I-NODE?
                    if (adjacent(neighbors2.get(k), m).contains(i))
                        return true;
                }
            }
        }

        // NO TRIANGLE
        return false;
    }

    public static void main(String args[]) {

        // @formatter:off
        int[][] m = {
            new int[] {0 , 1 , 0 , 0 , 1},
            new int[] {0 , 0 , 0 , 1 , 0},
            new int[] {0 , 1 , 0 , 0 , 0},
            new int[] {0 , 0 , 1 , 0 , 0},
            new int[] {0 , 0 , 0 , 1 , 0},
        };
        // @formatter:on

        // @formatter:off
        int[][] m2 = {
            new int[] {0 , 1 , 1 , 0 , 0 , 0},
            new int[] {0 , 0 , 0 , 0 , 0 , 0},
            new int[] {1 , 0 , 0 , 1 , 0 , 1},
            new int[] {1 , 0 , 0 , 0 , 0 , 0},
            new int[] {0 , 0 , 1 , 0 , 1 , 0},
            new int[] {0 , 1 , 0 , 1 , 1 , 0},
        };        
        // @formatter:on
        System.out.println("Spalte 1 =" + getColumn(1, m));
        System.out.println("Zeile 1  =" + getRow(1, m));

        // Ausgehende von 2 -> 0
        System.out.println(outDegree(1, m2));

        // Eingehende von 2 -> 2
        System.out.println(inDegree(1, m2));

        // neighbors from 6 -> 1, 3 and 4
        System.out.println(adjacent(5, m2));

        // true
        System.out.println(hasTriangle(m));
    }
}
