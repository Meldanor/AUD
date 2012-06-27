/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.training11;

import java.util.ArrayList;

import aud.example.graph.MyGraph;
import aud.example.graph.MyNode;

/**
 * @author Meldanor
 * 
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
        nodes.get(7).setLabel("Computer am Netz anschließen");
        nodes.get(1).setLabel("Internet-Recherche nach Musik");
        nodes.get(4).setLabel("MP3s für Partymusik zusammenstellen");

        nodes.get(10).setLabel("Aufgabenblatt AuD drucken");
        nodes.get(9).setLabel("AuD-Übung vorbereiten");

        nodes.get(2).setLabel("Abwaschen");
        nodes.get(5).setLabel("Müll rausbringen");

        nodes.get(14).setLabel("Schuhe putzen");

        nodes.get(8).setLabel("In die Stadt fahren");
        nodes.get(11).setLabel("Spülmittel kaufen");
        nodes.get(13).setLabel("Cola kaufen");
        nodes.get(12).setLabel("Freund/Freundin vom Bahnhof abholen");
        nodes.get(15).setLabel("aus der Stadt zurückkommen");

        
    }
}
