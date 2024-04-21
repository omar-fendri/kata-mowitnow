package com.omarfendri.kata.mowitnow.application.port.out;

import com.omarfendri.kata.mowitnow.domain.model.mower.Mower;

public interface MowerStateDisplayPort {
    String display(Mower mower);
}
