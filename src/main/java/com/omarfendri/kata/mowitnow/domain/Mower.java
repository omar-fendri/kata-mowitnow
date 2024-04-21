package com.omarfendri.kata.mowitnow.domain;

import com.omarfendri.kata.mowitnow.domain.enums.Orientation;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Mower {
    private final Coordinate coordinate;
    private final Orientation orientation;

    public Mower(Coordinate coordinate, Orientation orientation) {
        this.coordinate = coordinate;
        this.orientation = orientation;
    }
}
