package com.omarfendri.kata.mowitnow.domain;

import lombok.Builder;

@Builder
public class Grid {
    int length;
    int width;

    public Grid(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
