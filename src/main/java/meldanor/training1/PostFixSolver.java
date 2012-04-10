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
public class PostFixSolver {

    private static final Pattern S = Pattern.compile("\\s");
    private static final Pattern NUMBER = Pattern.compile("\\d");

    public static double solve(String line) {
        Stack<Double> values = new Stack<Double>();

        // REMOVE WHITESPACE BETWEEN DIGITS AND OPERATOR
        String[] split = S.split(line);

        // TEMP VALUESs
        Double value1 = null;
        Double value2 = null;

        // PARSING THE STRING
        for (String cha : split) {
            // IS NUMBER ?
            if (NUMBER.matcher(cha).matches())
                values.push(Double.valueOf(cha));

            // IS OPERATOR
            else {
                // GET LAST TWO VALUES
                value1 = values.pop();
                value2 = values.pop();

                // APPLY OPERATOR AND PUSH RESULT TO STACK
                if (cha.equals("+"))
                    values.push(value1 + value2);
                else if (cha.equals("-"))
                    values.push(value1 - value2);
                else if (cha.equals("/"))
                    values.push(value1 / value2);
                else if (cha.equals("*"))
                    values.push(value1 * value2);

                // UNKNOWN OPERATOR FOUND
                else
                    throw new RuntimeException("Unknown operator = " + cha);
            }
        }
        return values.pop();
    }

    public static void test() {
        System.out.println(solve("3 4 5 * - 6 5 + 2 - * "));
    }
}
