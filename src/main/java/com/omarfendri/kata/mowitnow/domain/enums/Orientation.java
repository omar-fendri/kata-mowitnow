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
    public static Orientation getFromChar(String character) {
        return switch (character) {
            case "N" -> NORTH;
            case "E" -> EAST;
            case "W" -> WEST;
            case "S" -> SOUTH;
            default -> throw new IllegalArgumentException("Unknown input");
        };
    }
    public String getSymbol() {
        return symbol;
    }
}
