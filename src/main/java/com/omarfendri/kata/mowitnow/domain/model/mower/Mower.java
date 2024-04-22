package com.omarfendri.kata.mowitnow.domain.model.mower;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Mower {
    private final Position position;
    private final Orientation orientation;


    public int getX(){
        return getPosition().getX();
    }

    public int getY() {
        return getPosition().getY();
    }
}
