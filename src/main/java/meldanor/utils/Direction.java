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
 * @author Meldanor
 * 
 */
public enum Direction {

    // @formatter:off
    /** From top to buttom */
    TOP_BOTTOM("TB"),
    /** From bottom to top */
    BOTTOM_TOP("BT"),
    /** From left to right side */
    LEFT_RIGHT("LR"),
    /** From right to left side */
    RIGHT_LEFT("RL");
    // @formatter:on

    private final String dirTag;

    private Direction(final String dirTag) {
        this.dirTag = dirTag;
    }

    public String getDirTag() {
        return dirTag;
    }

}
