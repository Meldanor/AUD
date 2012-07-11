/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training3;

/**
 * <pre>
 * Schreiben Sie eine Klasse LinkedNode zur Erzeugung von einfach verketteten Knoten in der abgebildeten Form.
 * 
 *     Die Klasse soll die Atribute
 * 
 *     public class LinkedNode {
 *        T data_;
 *        LinkedNode next_; 
 *        ...
 *     }
 * 
 *     enthalten. Schreiben Sie entsprechende Konstruktoren und die Getter-und Setter-Funktionen.
 *     Schreiben Sie eine Funktion
 * 
 *     public String toText(),
 * 
 *     die die Knoteninhalte von this bis zum Ende der verketteten Knoten ausgibt.
 *     Erzeugen Sie in main die unten abgebildeten verketteten Knoten und geben Sie diese Liste zur Kontrolle mit Hilfe der Funktion toText() aus (Aufruf für unten stehende Abbildung: head.toText()).
 *     Fügen Sie anschließend (in main) zwischen den Elementen "Weihnachten" und "Neujahr" ein weiteres Element "Silvester" ein und geben Sie auch diese Liste aus.
 *     Hinweis: Die Liste kann mit oder ohne head-Element implementiert werden. Die unterschiedlichen Ansätze werden in der Übung diskutiert.
 * </pre>
 */
public class LinkedNode<T> {

    private T data;
    private LinkedNode<T> next;

    public LinkedNode(T data) {
        this.data = data;
    }

    public LinkedNode(T data, LinkedNode<T> next) {
        this(data);
        this.next = next;
    }

    public LinkedNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public String toText() {
        StringBuilder sBuilder = new StringBuilder(1024);
        sBuilder.append("head");
        sBuilder.append('>');
        sBuilder.append(this.getData().toString());
        sBuilder.append('>');

        // ITERATE OVER THE LIST
        LinkedNode<T> current = this;
        while ((current = current.next) != null) {
            sBuilder.append(current.getData().toString());
            sBuilder.append('>');
        }

        // DELETE LAST '>'
        sBuilder.deleteCharAt(sBuilder.length() - 1);

        return sBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedNode<String> winterBegin = new LinkedNode<String>("Winteranfang");
        LinkedNode<String> newYear = new LinkedNode<String>("Neujahr");
        winterBegin.setNext(newYear);
        System.out.println(winterBegin.toText());

        LinkedNode<String> christmas = new LinkedNode<String>("Weihnachten", newYear);
        winterBegin.setNext(christmas);
        System.out.println(winterBegin.toText());
    }
}
