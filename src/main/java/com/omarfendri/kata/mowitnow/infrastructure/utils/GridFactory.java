package com.omarfendri.kata.mowitnow.infrastructure.utils;

import com.omarfendri.kata.mowitnow.domain.model.grid.Grid;

public class GridFactory {
    public static Grid fromString(String widthAndHeightLine) {
        String[] parts = widthAndHeightLine.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return Grid.builder()
                .width(x)
                .height(y)
                .build();
    }
}
