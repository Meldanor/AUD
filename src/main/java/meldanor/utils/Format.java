/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einhält:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

package meldanor.utils;

/**
 * Currently supported values:<br>
 * GIF <br>
 * DOT <br>
 * FIG <br>
 * PDF <br>
 * PS <br>
 * SVG <br>
 * PNG <br>
 * 
 * @author Meldanor
 * 
 */
public enum Format {

    // @formatter:off
    GIF("gif"),
    DOT("dot"),
    FIG("fig"),
    PDF("pdf"),
    PS("ps"),
    SVG("svg"),
    PNG("png");
    // @formatter:on

    private final String type;

    private Format(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
