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
import java.util.LinkedList;

/**
 * @author Meldanor
 * 
 */
public class SpecialTaskForce {

    public static void main(String[] args) {
        int[] edgeList = {6, 11, 1, 2, 1, 3, 3, 1, 3, 4, 3, 6, 4, 1, 5, 3, 5, 5, 6, 2, 6, 4, 6, 5};

        int[][] matrix = edgeListToAdjMatrix(edgeList);
        printMatrix(matrix);

        edgeListToAdjList(edgeList);
    }

    public static int[][] edgeListToAdjMatrix(int[] edgeList) {
        int[][] matrix = new int[edgeList[0]][edgeList[0]];

        for (int i = 2, j = 0, k = 0; i < edgeList.length; i += 2) {
            j = edgeList[i] - 1;
            k = edgeList[i + 1] - 1;
            matrix[j][k] = 1;
        }

        return matrix;
    }

    private static void printMatrix(int[][] a) {
        for (int i = 0; i < a.length; ++i) {

            for (int j = 0; j < a[i].length; ++j) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void edgeListToAdjList(int[] edgeList) {

        // EINE ARRAY LISTE DIE AUF UNTER LISTEN ZEIGT
        // ARRAY LISTE IST SO LANG WIE ES KNOTEN GIBT
        ArrayList<LinkedList<Integer>> adjList = new ArrayList<LinkedList<Integer>>(edgeList[0]);
        // ERSTELLE SUBLISTEN
        for (int i = 0; i < edgeList[0]; ++i)
            adjList.add(new LinkedList<Integer>());
        // LESE DIE KNOTEN EIN
        for (int i = 2, j = 0, k = 0; i < edgeList.length; i += 2) {
            j = edgeList[i] - 1;
            k = edgeList[i + 1] - 1;
            // HOLE DIE UNTER LISTE AN STELLE J (REPRÄSENTIERT DEN KNOTEN)
            // UND FÜGE ALS VERKNÜPFUNG DEN K-TEN KNOTEN HINZU
            adjList.get(j).add(k);
        }
    }
}
