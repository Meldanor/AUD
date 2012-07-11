/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training4;

import java.util.Random;

import aud.Stack;

/**
 * <pre>
 * Gegeben sei die rekursive Funktion
 * 
 *     public static int whatRec(int n) { 
 *         if (n < 10) 
 *             return n; 
 *         else 
 *             return whatRec(n / 10) + n % 10; 
 *     }
 * 
 *     Was macht whatRec?
 *     Schreiben Sie eine nichtrekursive Funktion
 * 
 *     public static int whatStack(int n),
 * 
 *     die einen Stack mit den üblichen Operationen push und pop nutzt und die gleiche Funktionalität wie whatRec bietet.
 * 
 * 
 * 
 * Hinweis:
 * 
 *     Die Klasse Stack<T> finden Sie unter den "Folien und Materialien" auf der Seite "Code/Beispiele". Die Klasse Stack<T> steht im Backend zur Verfügung und darf nicht importiert werden.
 * </pre>
 */
public class RecursionToStack {

    // digit sum
    public static int whatRec(int n) {
        if (n < 10)
            return n;
        else
            return whatRec(n / 10) + n % 10;
    }

    public static int whatStack(int n) {
        Stack<Integer> stack = new Stack<Integer>();
        int sum = 0;
        while (n >= 10) {
            stack.push(n);
            n /= 10;
        }
        sum = n;
        while (!stack.is_empty())
            sum += stack.pop() % 10;

        return sum;

    }

    public static int whatStack2(int n) {
        int sum = 0;
        while (n >= 10) {
            sum += n % 10;
            n /= 10;
        }

        return sum + n;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int j = 0;
        System.out.println("N\tRec\tStack\tIter");
        for (int i = 0; i < 10; ++i) {
            j = rand.nextInt(100) + 1;
            System.out.println(j + "\t" + whatRec(j) + "\t" + whatStack(j) + "\t" + whatStack2(j));
        }

    }

}
