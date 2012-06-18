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

import java.util.Random;

/**
 * @author Meldanor
 * 
 */
public class GraphConnectivity {
    public static int[][] matrixMult(int[][] a, int[][] b) {
        return matrixMultStrass(a, b);
//
//        if (a.length % 2 == 0)
//            return matrixMultStrass(a, b);
//        else
//            return matrixNormal(a, b);
    }

    private static int[][] matrixNormal(int[][] a, int[][] b) {

        int m = a.length;
        int l = a[0].length;
        int n = b[0].length;

        int[][] c = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < l; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return c;
    }

    private static int[][] matrixMultStrass(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];

        if ((n % 2 != 0) && (n != 1)) {
            int[][] a1, b1, c1;
            int n1 = n + 1;
            a1 = new int[n1][n1];
            b1 = new int[n1][n1];
            c1 = new int[n1][n1];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    a1[i][j] = a[i][j];
                    b1[i][j] = b[i][j];
                }
            c1 = matrixMultStrass(a1, b1);
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    result[i][j] = c1[i][j];
            return result;
        }

        if (n == 1)
            result[0][0] = a[0][0] * b[0][0];
        else {
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];

            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            divideArray(a, A11, 0, 0);
            divideArray(a, A12, 0, n / 2);
            divideArray(a, A21, n / 2, 0);
            divideArray(a, A22, n / 2, n / 2);

            divideArray(b, B11, 0, 0);
            divideArray(b, B12, 0, n / 2);
            divideArray(b, B21, n / 2, 0);
            divideArray(b, B22, n / 2, n / 2);

            int[][] P1 = matrixMultStrass(addMatrices(A11, A22), addMatrices(B11, B22));
            int[][] P2 = matrixMultStrass(addMatrices(A21, A22), B11);
            int[][] P3 = matrixMultStrass(A11, subtractMatrices(B12, B22));
            int[][] P4 = matrixMultStrass(A22, subtractMatrices(B21, B11));
            int[][] P5 = matrixMultStrass(addMatrices(A11, A12), B22);
            int[][] P6 = matrixMultStrass(subtractMatrices(A21, A11), addMatrices(B11, B12));
            int[][] P7 = matrixMultStrass(subtractMatrices(A12, A22), addMatrices(B21, B22));

            int[][] C11 = addMatrices(subtractMatrices(addMatrices(P1, P4), P5), P7);
            int[][] C12 = addMatrices(P3, P5);
            int[][] C21 = addMatrices(P2, P4);
            int[][] C22 = addMatrices(subtractMatrices(addMatrices(P1, P3), P2), P6);

            copySubArray(C11, result, 0, 0);
            copySubArray(C12, result, 0, n / 2);
            copySubArray(C21, result, n / 2, 0);
            copySubArray(C22, result, n / 2, n / 2);
        }
        return result;
    }

    private static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;

        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] + B[i][j];

        return result;
    }

    private static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;

        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] - B[i][j];

        return result;
    }

    private static void divideArray(int[][] parent, int[][] child, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < child.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < child.length; j1++, j2++) {
                child[i1][j1] = parent[i2][j2];
            }
    }

    private static void copySubArray(int[][] child, int[][] parent, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < child.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < child.length; j1++, j2++) {
                parent[i2][j2] = child[i1][j1];
            }
    }

    private static void printMatrix(int[][] a) {
        for (int i = 0; i < a.length; ++i) {

            for (int j = 0; j < a[i].length; ++j) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // @formatter:off
        int[][] a = {
                new int[] { 1 , 2 , 3 },
                new int[] { 4 , 5 , 6 }
        };

        int[][] b = {
                new int[] { 6 , -1 },
                new int[] { 3 , 2 },
                new int[] { 0 , -3 }
        };
        
        // @formatter:on

//        printMatrix(a);
//        System.out.println();
//        printMatrix(b);

        Random rand = new Random();
        int size = (rand.nextInt(20) + 20) * 2;
        int[][] d = new int[size][size];
        int[][] e = new int[size][size];

        for (int i = 0; i < d.length; ++i) {
            for (int j = 0; j < d.length; ++j) {
                d[i][j] = rand.nextInt(100);
                e[i][j] = rand.nextInt(100);
            }
        }

        long time = System.nanoTime();
        long t1 = 0L;
        long t2 = 0L;
        int tests = 10;

        for (int i = 0; i < tests; ++i) {
            time = System.nanoTime();
            matrixNormal(d, e);
            time = System.nanoTime() - time;
            t2 += time;

            time = System.nanoTime();
            matrixMultStrass(d, e);
            time = System.nanoTime() - time;
            t1 += time;

        }
        System.out.println("Strassen-Algorithmus=" + t1 / tests);
        System.out.println("Normaler-Algorithmus=" + t2 / tests);
    }
}
