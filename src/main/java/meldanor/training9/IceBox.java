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

import java.util.Arrays;

/**
 * <pre>
 *     Schreiben Sie die Klasse Food, die für jedes Lebensmittel einen Oberbegriff (z.B. Obst) und eine Bezeichnung (z.B. Melone) speichert. Weitere Merkmale können nach Belieben hinzugefügt werden.
 *     Neben einem geeigneten Konstruktor und der Methode toString() ist in dieser Klasse eine Methode int hashCode() zu schreiben, die den Hashcode des Food-Objektes berechnet. Überlegen Sie, welche Klassenmerkmale Sie in die Berechnung des Hashwertes einbeziehen.
 *     In einer weiteren Klasse Icebox soll neben geeigneten Attributen und Konstruktoren die Methoden
 * 
 *     public void add(T obj)
 * 
 *     und
 * 
 *     public boolean contains(T obj)
 * 
 *     implementiert werden, die Ihre Hashtabelle nach einer Kollisionsstrategie Ihrer Wahl füllt bzw. darin sucht. Begründen Sie die Wahl Ihrer Strategie.
 *     Erzeugen Sie im Testrahmen zehn verschiedene Food-Objekte, die Sie in Ihren Kühlschrank packen. Geben Sie zur Kontrolle den Inhalt Ihrer IceBox aus.
 *     Welche alternativen Möglichkeiten zum Speichern und Wiederauffinden solcher Food-Objekte halten Sie auch noch für geeignet? Begründen Sie kurz.
 * </pre>
 */
public class IceBox<T> {

    private T[] table;
    private int size; // current number of elements
    private int capacity;

    private int loadFactor;

    private static final int MIN_SIZE = 13;

    public IceBox() {
        this(MIN_SIZE);
    }

    @SuppressWarnings("unchecked")
    private IceBox(int capacity) {
        this.capacity = capacity;
        table = (T[]) new Object[capacity];
        loadFactor = (int) (capacity * 0.75);
    }

    private int hash(T obj) {
        return Math.abs(obj.hashCode()) % capacity;
    }

    public void add(T obj) {
        addQuad(obj);
    }

    public int addQuad(T obj) {
        // WE NEED TO RESIZE THE HASH TABLE
        if (size >= loadFactor)
            resize();

        // COUNTER FOR COLLISIONS
        int collisions = 0;

        // SEARCH FOR FREE SPACE
        int i = 0;
        int hash = hash(obj);
        int temp = hash;
        while (table[temp] != null) {
            ++collisions;
            ++i;
            temp = Math.abs((hash + (i * i))) % capacity;
        }

        // INSERT VALUE
        table[temp] = obj;
        ++size;

        return collisions;
    }

    private void resize() {
        // TEMP HASH MAP WITH TWICE SIZE
        IceBox<T> t = new IceBox<T>(size << 1);
        for (int i = 0; i < size; ++i) {
            // RE-HASH
            if (table[i] != null)
                t.add(table[i]);

        }

        this.capacity = t.capacity;
        this.size = t.size;
        this.table = t.table;
        this.loadFactor = t.loadFactor;

    }

    public String toString() {
        return Arrays.toString(table);
    }

    public boolean contains(T obj) {

        // SEARCH FOR FREE SPACE
        int i = 0;
        int hash = hash(obj);
        int temp = hash;
        while (table[temp] != null) {
            if (table[temp].equals(obj))
                return true;
            else {
                ++i;
                temp = Math.abs((hash + (i * i))) % capacity;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Food cola = new Food("Getraenk", "Cola");

        IceBox<Food> box = new IceBox<Food>();
        // @formatter:off
        Food[] myFood = {
                new Food("Obst","Apfel"),
                new Food("Gemuese","Kartoffeln"),
                new Food("Gemuese","Zwiebeln"),
                new Food("Getraenk","Milch"),
                new Food("Getraenk","Orangensaft"),
                new Food("Kaese","Gauda"),
                new Food("Wurst","Salami"),
                new Food("Wurst","Leberwurst"),
                new Food("Marmelade","Erdbeer"),
                new Food("Marmelade","Aprikose"),
                new Food("Marmelade","Johannisbeere"),
                cola
        };
        // @formatter:on

        for (Food food : myFood)
            box.add(food);

        System.out.println(box);
        System.out.println(box.contains(cola));

    }
}
