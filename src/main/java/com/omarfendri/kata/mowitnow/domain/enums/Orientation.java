package com.omarfendri.kata.mowitnow.domain.enums;

public enum Orientation {
    NORTH('N'),
    EAST('E'),
    WEST('W'),
    SOUTH('S');

    public final String symbol;
    Orientation(char symbol) {
        this.symbol = String.valueOf(symbol);
    }

    public String getSymbol() {
        return symbol;
    }
}
