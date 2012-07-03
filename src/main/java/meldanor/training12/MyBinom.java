/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training12;

/**
 * @author Meldanor
 * 
 */
public class MyBinom {

    private static int[][] results;

    public static int binom(int n, int k) {
        // FAST RETURN -> DON'T NEED TO INITATE THE ARRAY
        if (k == 0 || n == k)
            return 1;
        if (k == 1 || n == k + 1)
            return n;

        results = new int[n + 1][k + 1];

        // |n| = 1
        // |0|
        //
        // |n| = n
        // |1|
        for (int i = 0; i < results.length; ++i) {
            results[i][0] = 1;
            results[i][1] = i;
        }

        // |n| = 1
        // |n|
        //
        // |n | = n
        // |n-1|
        for (int i = 0; i < results.length && i < results[i].length; ++i) {
            results[i][i] = 1;
            if (i > 0)
                results[i][i - 1] = i;
        }

        return binomRek(n, k);
    }

    private static int binomRek(int n, int k) {
        // IS THE PARTIAL SOLUTION EXISTING?
        if (results[n][k] == 0)
            results[n][k] = binomRek(n - 1, k) + binomRek(n - 1, k - 1);

        return results[n][k];
    }

    private static void printMatrix(int[][] a) {
        if (a == null) {
            System.out.println("Matrix is null!");
            return;
        }
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a[i].length; ++j) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(binom(7, 5));
        printMatrix(results);
    }

}
