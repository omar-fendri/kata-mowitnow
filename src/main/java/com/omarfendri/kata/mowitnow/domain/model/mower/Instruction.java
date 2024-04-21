package com.omarfendri.kata.mowitnow.domain.model.mower;

public enum Instruction {
    RIGHT('D'),
    LEFT('G'),
    FORWARD('A');
    private final String symbol;

    Instruction(char symbol) {
        this.symbol = String.valueOf(symbol);
    }
    public static <Char> Instruction getFromChar(char character) {
        return switch (character) {
            case 'D' -> RIGHT;
            case 'G' -> LEFT;
            case 'A' -> FORWARD;
            default -> throw new IllegalArgumentException("Unknown input");
        };
    }
    public String getSymbol() {
        return symbol;
    }
}
