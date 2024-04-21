package com.omarfendri.kata.mowitnow.domain.model.grid;

import lombok.Builder;

@Builder
public class Grid {
    int height;
    int width;
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
