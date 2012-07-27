/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einh�lt:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training11;

import java.util.ArrayList;

import aud.example.graph.MyGraph;
import aud.example.graph.MyNode;

/**
 * <pre>
 * Heute Abend m�chte ich zur Party gehen. Vorher muss ich aber noch folgende Dinge erledigen:
 * 
 *         1. Mathehausaufgaben machen      9. Internet-Recherche nach Musik
 *         2. Abwaschen    10. Computer aufbauen
 *         3. MP3s f�r Partymusik zusammenstellen  11. M�ll rausbringen
 *         4. Mathebuch aus Bibliothek holen           12. Computer am Netz anschlie�en
 *         5. In die Stadt fahren  13. Sp�lmittel kaufen
 *         6. AuD-�bung vorbereiten    14. Aufgabenblatt AuD drucken
 *         7. Freund / Freundin vom Bahnhof abholen        15. Cola kaufen
 *         8. Schuhe putzen    16. aus der Stadt zur�ckkommen
 * 
 *     Stellen Sie die obigen Aufgaben mit ihren Abh�ngigkeiten anschaulich in einem Graphen dar. Dieser Graph soll mindestens 20 Kanten besitzen.
 *     Wie sieht eine topologisch sortierte Reihenfolge des Graphen aus? Erkl�ren Sie den Algorithmus. Gibt es weitere Varianten?
 * 
 * Hinweis:
 * Die Antwort f�r Teil a) k�nnen Sie auf Papier mitbringen, Teil b) m�ssen Sie aber einreichen.
 * </pre>
 */
public class TopologicalSorting {

    public static void main(String[] args) {

        MyGraph graph = new MyGraph(true);
        ArrayList<MyNode> nodes = new ArrayList<MyNode>(20);
        // GENERATE NODES
        for (int i = 0; i < 16; ++i)
            nodes.add(graph.addNode());

        // SET LABELS
        nodes.get(6).setLabel("Mathebuch aus Bibliothek holen");
        nodes.get(0).setLabel("Mathehausaufgaben machen");

        nodes.get(3).setLabel("Computer aufbauen");
        nodes.get(7).setLabel("Computer am Netz anschlie�en");
        nodes.get(1).setLabel("Internet-Recherche nach Musik");
        nodes.get(4).setLabel("MP3s f�r Partymusik zusammenstellen");

        nodes.get(10).setLabel("Aufgabenblatt AuD drucken");
        nodes.get(9).setLabel("AuD-�bung vorbereiten");

        nodes.get(2).setLabel("Abwaschen");
        nodes.get(5).setLabel("M�ll rausbringen");

        nodes.get(14).setLabel("Schuhe putzen");

        nodes.get(8).setLabel("In die Stadt fahren");
        nodes.get(11).setLabel("Sp�lmittel kaufen");
        nodes.get(13).setLabel("Cola kaufen");
        nodes.get(12).setLabel("Freund/Freundin vom Bahnhof abholen");
        nodes.get(15).setLabel("aus der Stadt zur�ckkommen");

    }
}
