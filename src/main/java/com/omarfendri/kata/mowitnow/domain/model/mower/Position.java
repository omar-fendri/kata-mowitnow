package com.omarfendri.kata.mowitnow.domain.model.mower;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Position {
    int x;
    int y;
}
