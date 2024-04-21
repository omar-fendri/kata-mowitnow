package com.omarfendri.kata.mowitnow.domain;

import com.omarfendri.kata.mowitnow.domain.enums.Orientation;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Mower {
    private final Position position;
    private final Orientation orientation;

    public Mower(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public int getX(){
        return getPosition().getX();
    }

    public int getY() {
        return getPosition().getY();
    }
}
