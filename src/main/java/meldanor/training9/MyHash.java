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
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * <pre>
 * Implementieren Sie eine Hash-Funktion, die 1001 Worte (alle Worte aus Faust 1, zufällig ausgewählt, ohne Dopplungen, alle ohne Umlaute und ß) in eine Hashtabelle der Größe 1249 (Primzahl) so einträgt (keine Verkettung der Überläufer), dass möglichst wenig Kollisionen entstehen. Zum Testen können Sie folgende Beispieldatei mit 1001 Worten (ww1.txt) von Namen (ohne ö, ä, ü, ß und Sonderzeichen) nutzen.
 * 
 * Hinweise:
 * 
 *     Nutzen Sie keine vordefinierten Klassen und Methoden zur Berechnung der Hash-Funktion wie z.B. die Klasse Hashtable oder die Methode hashCode aus der Klasse String.
 *     Die Überprüfung Ihres Java-Programms zur Realisierung der obigen Hash-Funktion erfolgt im Rahmen der Übung an einer für Sie unbekannten Datei mit ebenfalls 1001 Datensätzen.
 * 
 * 
 * Hinweise:
 * 
 *     Als Ergebnis des Backend-Tests mit der Datei w1.txt kommt bei korrektem Programm folgende automatische Rückmeldung:
 * 
 *     Your submission failed.
 * 
 *     testGetcol(JUnitTester): getCollisions: (number of collisions) 
 * 
 *     Das ist ein "erwünschte" Fehleranzeige, um die Ausgabe der Anzahl der Kollisionen zu erzwingen. Ihre Aufgabe kann damit nicht automatisch den Status "akzeptiert" bekommen, sondern erhält den Status "eingereicht".
 * </pre>
 */
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
            if (!primNumbers.containsKey(Character.toLowerCase(chars[i])))
                System.out.println("s= " + s + " und c= " + chars[i]);
            hash -= primNumbers.get(Character.toLowerCase(chars[i])) * hash + chars[i] - i;
        }

        return hash;
    }

    public void insert(String s) {
        e.insert(Math.abs(getHash(s)) % size, s);
    }

    public static void main(String[] args) {
        // Idea for test
        int size = 10091; // you have to use this value
        MyHash hash = new MyHash(size);
        try {
            // USE BUFFERED READER INSTEAD OF DATAINPUTSTREAM (IS FASTER)
            BufferedReader s = new BufferedReader(new FileReader("faust.txt"));

            TreeSet<String> set = new TreeSet<String>();
            // use correct Path
            String line = "";
            // READ FROM FILE AND SORT OUT DOUBLE WORDS
            while ((line = s.readLine()) != null)
                set.add(line);

            // INSERT INTO HASH MAP UNICATE WORDS
            for (String string : set)
                hash.insert(string);

            // CLOSE THE STREAM...
            s.close();

            System.out.println(hash.e.toString());
            System.out.println("Collisions: " + hash.e.getCollisions() + "\n");

        } catch (IOException e) {
            System.out.println("File not found");
        }

    }
}
