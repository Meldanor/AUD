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

import java.lang.reflect.Field;

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
public class Food {

    // IMMUTABLE OBJECT
    private final String foodClass;
    private final String name;

    private final int hash;

    public Food(String foodClass, String name) {
        this.foodClass = foodClass;
        this.name = name;
        hash = calculateHash();
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder(getClass().getSimpleName());
        sBuilder.append("(");
        try {
            Field[] fields = getClass().getDeclaredFields();
            for (Field field : fields) {
                sBuilder.append(field.getName());
                sBuilder.append("='");
                sBuilder.append(field.get(this));
                sBuilder.append("',");
            }

            sBuilder.setCharAt(sBuilder.length() - 1, ')');

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sBuilder.toString();

    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Food))
            return false;

        Food otherFood = (Food) obj;

        return this.foodClass.equals(otherFood.foodClass) && this.name.equals(otherFood.name);

    }

    private int calculateHash() {
        int tempHash = 17;
        try {
            Field[] fields = getClass().getDeclaredFields();

            for (Field field : fields) {
                if (!field.getName().equals("hash"))
                    tempHash = 31 * tempHash * field.get(this).hashCode();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tempHash;
    }

    public String getFoodClass() {
        return foodClass;
    }

    public String getName() {
        return name;
    }
}
