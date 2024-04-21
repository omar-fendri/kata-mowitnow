package com.omarfendri.kata.mowitnow.domain.enums;

public enum Instruction {
    RIGHT('D'),
    LEFT('G'),
    FORWARD('A');
    private final String symbol;

    Instruction(char symbol) {
        this.symbol = String.valueOf(symbol);
    }

    public String getSymbol() {
        return symbol;
    }
}
