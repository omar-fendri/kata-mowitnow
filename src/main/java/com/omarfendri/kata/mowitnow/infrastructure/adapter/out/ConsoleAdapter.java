package com.omarfendri.kata.mowitnow.infrastructure.adapter.out;

import com.omarfendri.kata.mowitnow.application.port.out.MowerStateDisplayPort;
import com.omarfendri.kata.mowitnow.domain.model.mower.Mower;

public class ConsoleAdapter implements MowerStateDisplayPort {
    @Override
    public String display(Mower mower) {
        String output = "%d %d %s\n".formatted(
                mower.getX(),
                mower.getY(),
                mower.getOrientation().getSymbol());
        System.out.print(output);
        return output;
    }
}
