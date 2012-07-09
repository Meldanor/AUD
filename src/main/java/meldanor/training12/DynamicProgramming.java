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
 * @author Meldanor
 * 
 */
public class DynamicProgramming {

    private static int[][] results;

    // DYNAMIC PROGRAMMING

    public static int f(int a, int b) {
        // FAST RETURN -> DON'T NEED TO INITATE THE ARRAY
        if (a == 0 || b == 0)
            return 1;

        results = new int[a + 1][b + 1];
        for (int i = 0; i < results.length; ++i)
            results[i][0] = 1;
        for (int i = 0; i < results[0].length; ++i)
            results[0][i] = 1;

        return fRek(a, b);
    }

    private static int fRek(int a, int b) {
        // IS THE PARTIAL SOLUTION EXISTING?
        if (results[a][b] == 0)
            results[a][b] = fRek(a - 1, b) + (2 * fRek(a, b - 1));

        return results[a][b];
    }

    // NON-DYNAMIC PROGRAMMING

    public static int f2(int a, int b) {
        if (a == 0 || b == 0)
            return 1;
        else
            return f2(a - 1, b) + (2 * f2(a, b - 1));
    }

    private static final int TEST_COUNTER = 50;
    private static final int MAX_A = 15;
    private static final int MAX_B = 15;

    public static void main(String[] args) {

        // GENERATE TEST NUMBERS

        int[][] testPairs = new int[TEST_COUNTER][2];
        Random rand = new Random();

        for (int i = 0; i < TEST_COUNTER; ++i) {
            testPairs[i][0] = rand.nextInt(MAX_A);
            testPairs[i][1] = rand.nextInt(MAX_B);
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
            System.out.println("Dynamic:\ta=" + a + "\tb=" + b + "\tResult = " + f(a, b));
            total1 += System.nanoTime() - t1;

            // NON DYNAMIC
            t2 = System.nanoTime();
            System.out.println("Non-Dynamic:\ta=" + a + "\tb=" + b + "\tResult = " + f2(a, b));
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
