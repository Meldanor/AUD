/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training1;

/**
 * <pre>
 *     Beschreiben Sie kurz was eine Endrekursion (iterative Rekursion, engl.tail recursion) ist.
 *     Schreiben Sie jeweils eine endrekursive Methode.
 *         
 *         Es soll mit der endrekursive Methode
 * 
 *         <b>public static int pot2TailRec(int n)</b>
 * 
 *         die Zweierpotenz einer natürlichen Zahl n berechnet werden. So soll beispielsweise pot2TailRec(4) die Zahl 16 liefern.
 *         Für die Bestimmung der befreundeten Zahlen (siehe Blatt03 Wintersemester) benötigt man die Summe der Teiler einer natürlichen Zahl n. Dabei wird 1 als Teiler von n betrachtet und mitsummiert, die Zahl n selbst aber nicht. Beispielsweise ist die Summe der Teiler von 6 gleich 1 + 2 + 3. Schreiben Sie eine endrekursive Methode
 * 
 *         <b>static public int sumFacTailRec(int n)</b>
 * 
 *         zur Berechnung der Summe der Teiler von n.
 *         Testen Sie beide Methoden gewissenhaft an geeigneten Beispielen, damit kein StackOverflow auftritt.
 * </pre>
 */
public class TailRecursion {

    /*
     * a) Eine Endrekursion ist eine Methode, wo der Rekursionsschritt die
     * letzte Anweisung der Methode ist und danach keine weiteren Anweisungen
     * folgen.
     */

    public static int pot2TailRec(int n) {
        return pot2TailRec(1, n);
    }

    private static int pot2TailRec(int m, int i) {
        if (i == 0)
            return m;
        else
            return pot2TailRec(m * 2, --i);
    }

    public static int sumFacTailRec(int n) {
        return sumFacTailRec(n, 1, n / 2);
    }

    private static int sumFacTailRec(int n, int sum, int count) {
        if (count == 1)
            return sum;
        else {
            if (n % count == 0)
                return sumFacTailRec(n, sum + count, --count);
            else
                return sumFacTailRec(n, sum, --count);
        }
    }

    public static void main(String[] args) {
        // 1024
        System.out.println("2^10 = " + pot2TailRec(10));
        // 2147483647
        System.out.println("(2^31) - 1 = " + (pot2TailRec(31) - 1));
        // -2147483648
        System.out.println("(2^31) = " + (pot2TailRec(31)));

        System.out.println();

        // 7
        System.out.println("Teilersumme von 8 = " + sumFacTailRec(8));
        // 15
        System.out.println("Teilersumme von 16 = " + sumFacTailRec(16));
        // 1
        System.out.println("Teilersumme von 13 = " + sumFacTailRec(13));
        // 21 -> 9 + 6 + 3 + 2 + 1
        System.out.println("Teilersumme von 18 = " + sumFacTailRec(18));

        System.out.println();

        for (int i = 2; i < 128; ++i)
            System.out.println("Teilersumme von " + i + " = " + sumFacTailRec(i));
    }
}
