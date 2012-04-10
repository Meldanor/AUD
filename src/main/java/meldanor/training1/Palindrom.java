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
 * @author Meldanor
 * 
 */
public class Palindrom {

    private static final Pattern p = Pattern.compile("[^a-zA-Z0-9]");

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
    public static void test() {

        String[] tests = {"Reliefpfeiler", "lol", "oman", "Meine Haare lichten sich", "Lagerregal", "Ein Esel lese nie.", "Na, Fakir, Paprika-Fan?", "Madam", "Marktkram", "Gurken hol ohne Krug!", "O, Streit irritiert so!"};

        for (String test : tests)
            System.out.println("'" + test + "' is palindrome? " + isPalindrome(test));
    }
}
