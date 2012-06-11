/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyHash {
    public class BucketArray {
        // do not change because of Backendtest
        protected String[] arr;
        protected int col;

        public BucketArray(int size) {
            col = 0;
            arr = new String[size];
        }

        public boolean insert(int pos, String s) {
            if (arr[pos] == null) {
                arr[pos] = s;
                return true;
            }
            col++;
            return false;
        }

        public int getCollisions() {
            return col;
        }

        public String toString() {
            String res = new String();

            for (String s : arr)
                res += s + "\n";

            return res;
        }
    }

    public BucketArray e; // use only this array!!
    int size;

    public MyHash(int size) {
        this.size = size;
        e = new BucketArray(size);
    }

    private static Map<Character, Integer> primNumbers = new HashMap<Character, Integer>();

    // RELATIVE FREQUENCE OF CHARS IN GERMAN ALPHABET © wikipedia.de
    // E 17,40 %
    // N 9,78 %
    // I 7,55 %
    // S 7,27 %
    // R 7,00 %
    // A 6,51 %
    // T 6,15 %
    // D 5,08 %
    // H 4,76 %
    // U 4,35 %
    // L 3,44 %
    // C 3,06 %
    // G 3,01 %
    // M 2,53 %
    // O 2,51 %
    // B 1,89 %
    // W 1,89 %
    // F 1,66 %
    // K 1,21 %
    // Z 1,13 %
    // P 0,79 %
    // V 0,67 %
    // J 0,27 %
    // Y 0,04 %
    // X 0,03 %
    // Q 0,02 %

    // FILL THE MAP
    static {
        primNumbers.put('e', 31);
        primNumbers.put('n', 37);
        primNumbers.put('i', 41);
        primNumbers.put('s', 43);
        primNumbers.put('r', 47);
        primNumbers.put('a', 53);
        primNumbers.put('t', 59);
        primNumbers.put('d', 61);
        primNumbers.put('h', 67);
        primNumbers.put('u', 71);
        primNumbers.put('l', 73);
        primNumbers.put('c', 79);
        primNumbers.put('g', 83);
        primNumbers.put('m', 89);
        primNumbers.put('o', 97);
        primNumbers.put('b', 101);
        primNumbers.put('w', 103);
        primNumbers.put('f', 107);
        primNumbers.put('k', 109);
        primNumbers.put('z', 113);
        primNumbers.put('p', 127);
        primNumbers.put('v', 131);
        primNumbers.put('j', 149);
        primNumbers.put('y', 151);
        primNumbers.put('x', 157);
        primNumbers.put('q', 163);

    }

    private int getHash(String s) {
        int hash = 0;
        char[] chars = s.toCharArray();

        // LET THE MAGIC BEGIN
        for (int i = 0; i < chars.length; ++i) {
            hash = primNumbers.get(Character.toLowerCase(chars[i])) * hash + chars[i];
        }

        return hash;
    }

    public void insert(String s) {
        System.out.println(s);
//        e.insert(Math.abs(getHash(s)) % size, s);
        e.insert(Math.abs(s.hashCode() % size), s);
    }

    public static void main(String[] args) {
        // Idea for test
        int size = 1249; // you have to use this value
        MyHash hash = new MyHash(size);
        try {
            File f = new File("w1.txt");
            System.out.println(f.getAbsolutePath());
            BufferedReader s = new BufferedReader(new FileReader("w1.txt"));
            // use correct Path
            String line = "";
            while ((line = s.readLine()) != null)
                hash.insert(line);
            s.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
        System.out.println(hash.e.toString());
        System.out.println("Collisions: " + hash.e.getCollisions() + "\n");
    }
}
