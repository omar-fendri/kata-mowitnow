package com.omarfendri.kata.mowitnow.domain;

import lombok.Builder;

@Builder
public class Grid {
    int height;
    int width;

    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
