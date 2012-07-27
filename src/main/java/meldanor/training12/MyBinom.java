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

import java.text.DecimalFormat;
import java.util.Random;

/**
 * <pre>
 *     Beschreiben Sie im Pseudocode den Algorithmus, um den Binomialkoeffizienten unter Ausnutzung der Rekursionsformel
 *     mit Hilfe der dynamischen Programmierung zu berechnen. Wie groß ist die Komplexität in O-Notation in Abhängigkeit von n und k?
 * 
 *     Geben Sie zur Illustration der Arbeitsweise des Algorithmusses die berechnete Tabelle an für die Eingaben n = 7 und k = 5.
 *     Formulieren Sie für die Berechnung des Binomialkoeffizienten mit dynamischer Programmierung die Java-Methode
 * 
 *     public static int binom(int n, int k)
 * 
 *     Überlegen Sie, wie die Anzahl der Rechenschritte reduziert werden kann.
 *     Vergleichen Sie den Aufwand zur Berechnung mit einer rekursiven Lösung, indem Sie die obige Rekursionsformel direkt (Baumrekursion) verwenden.
 * </pre>
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

    public static int binom2(int n, int k) {
        if (k == 0 || n == k)
            return 1;
        if (k == 1 || n == k + 1)
            return n;
        else
            return binom2(n - 1, k) + binom2(n - 1, k - 1);
    }

    // NOT USED
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

    private static final int TEST_COUNTER = 200;
    private static final int MAX_A = 20;
    private static final int MAX_B = 20;

    public static void main(String[] args) {

        // GENERATE TEST NUMBERS

        int[][] testPairs = new int[TEST_COUNTER][2];
        Random rand = new Random();

        for (int i = 0; i < TEST_COUNTER; ++i) {
            testPairs[i][0] = rand.nextInt(MAX_A);
            testPairs[i][1] = rand.nextInt(MAX_B);
            // k must be lower than n
            if (testPairs[i][0] < testPairs[i][1])
                --i;
        }

        // MEASURE VALUES

        long t1 = System.currentTimeMillis();
        long t2 = System.currentTimeMillis();

        long total1 = 0L;
        long total2 = 0L;

        // TEMP VAR FOR BETTER READING
        int a = 0;
        int b = 0;

        for (int i = 0; i < TEST_COUNTER; ++i) {
            a = testPairs[i][0];
            b = testPairs[i][1];

            // DYNAMIC
            t1 = System.nanoTime();
            System.out.println("Dynamic:\ta=" + a + "\tb=" + b + "\tResult = " + binom(a, b));
            total1 += System.nanoTime() - t1;

            // NON DYNAMIC
            t2 = System.nanoTime();
            System.out.println("Non-Dynamic:\ta=" + a + "\tb=" + b + "\tResult = " + binom2(a, b));
            total2 += System.nanoTime() - t2;

            System.out.println();
        }

        // PRINT OUT RESULTS
        total1 /= TEST_COUNTER;
        total2 /= TEST_COUNTER;

        double percent1 = (double) total1 / (double) total2 * 100.0;
        double percent2 = (double) total2 / (double) total1 * 100.0;

        DecimalFormat f = new DecimalFormat("#0.00");

        System.out.println("Dynamic:\t" + total1 + " ns\t(" + f.format(percent1) + "% of Non-Dynamic)");
        System.out.println("Non-Dynamic:\t" + total2 + " ns\t(" + f.format(percent2) + "% of Dynamic)");

    }
}
