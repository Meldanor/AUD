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

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * <pre>
 * Eine gegebene Zeichenkette (String) ist ein Palindrom, wenn sie vorwärts und rückwärts gelesen gleich ist. Schreiben Sie eine Methode
 * 
 * public static boolean isPalindrome(String str),
 * 
 * die bei einem übergebenen String str ermittelt, ob str ein Palindrom ist oder nicht. Dabei sollen in str Leer-, Sonder- und Satzzeichen überlesen werden. Außerdem soll die Unterscheidung von Groß- und Kleinbuchstaben entfallen. Benutzen Sie bei der Lösung nicht die vordefinierte Methode reverse, sondern die Klasse Stack<T>
 * 
 * Als Testdaten können Sie folgende Palindrome benutzen:
 * 
 *     Reliefpfeiler
 *     Madam
 *     Lagerregal
 *     Marktkram
 *     Ein Esel lese nie.
 *     Gurken hol ohne Krug!
 *     Na, Fakir, Paprika-Fan? 
 *     O, Streit irritiert so!
 * 
 * Hinweise:
 * 
 *     - Hier finden Sie AbstractStack und Stack aus der Vorlesung. Die Klasse Stack<T> steht im Backend zur Verfügung und darf nicht importiert werden.
 *     - Sie können aber auch die vordefinierte Klasse java.util.Stack<T> nutzen. Dann müssen Sie aber die Klasse Stack importieren mit
 *       import java.util.Stack;
 * </pre>
 */
public class Palindrom {

    private static final Pattern p = Pattern.compile("[^a-zA-Z]");

    public static boolean isPalindrome(String str) {
        // ELIMINATE ALL SPECIAL CHARACTER AND CAST TO LOWER CASE
        str = p.matcher(str).replaceAll("").toLowerCase();

        // STORE CHARS IN FIRST STACK
        Stack<Character> s1 = new Stack<Character>();
        for (Character cha : str.toCharArray())
            s1.push(cha);

        // SECOND STACK
        Stack<Character> s2 = new Stack<Character>();

        Character cha;

        // TEST PALINDROME
        while (!s1.isEmpty()) {
            // GET FIRST CHAR
            cha = s1.pop();

            // ONLY ONE CHAR LEFT - CHECK FOR ODD STRING LENGTH
            if (s1.isEmpty())
                return true;

            // REVERSE STACK
            while (!s1.isEmpty())
                s2.push(s1.pop());

            // FIRST AND LAST ELEMENT OF THE STRING ARE NOT EQUAL!
            if (!cha.equals(s2.pop()))
                return false;

            // ONLY ONE CHAR LEFT - CHECK FOR ODD STRING LENGTH
            if (s2.isEmpty())
                return true;

            // GET FIRST CHAR OF REVERSED STRING
            cha = s2.pop();

            // COPY IN REVERSE TO FIRST STACK
            while (!s2.isEmpty())
                s1.push(s2.pop());

            // ONLY ONE CHAR LEFT - CHECK FOR ODD STRING LENGTH
            if (s1.isEmpty())
                return true;

            // FIRST AND LAST ELEMENT OF THE STRING ARE NOT EQUAL!
            if (!cha.equals(s1.pop()))
                return false;
        }

        // IS PALINDROME
        return true;
    }

    public static void main(String[] args) {

        String[] tests = {"Reliefpfeiler", "lol", "oman", "Meine Haare lichten sich", "Lagerregal", "Ein Esel lese nie.", "Na, Fakir, Paprika-Fan?", "Madam", "Marktkram", "Gurken hol ohne Krug!", "O, Streit irritiert so!"};

        for (String test : tests)
            System.out.println("'" + test + "' is palindrome? " + isPalindrome(test));
    }
}
