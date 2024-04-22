package com.omarfendri.kata.mowitnow.domain.model.mower;

import lombok.Getter;

@Getter
public enum Orientation {
    NORTH('N'),
    EAST('E'),
    WEST('W'),
    SOUTH('S');

    public final String symbol;
    Orientation(char symbol) {
        this.symbol = String.valueOf(symbol);
    }
    public static Orientation getFromChar(char character) {
        return switch (character) {
            case 'N' -> NORTH;
            case 'E' -> EAST;
            case 'W' -> WEST;
            case 'S' -> SOUTH;
            default -> throw new IllegalArgumentException("Unknown input");
        };
    }
}
