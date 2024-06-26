package com.omarfendri.kata.mowitnow.domain.model.mower;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class AutonomousMower {
    Mower mower;
    List<Instruction> instructionsList;

    public void addInstruction(Instruction instruction) {
        instructionsList.add(instruction);
    }

    public int getX() {
        return getMower().getX();
    }

    public int getY() {
        return getMower().getY();
    }

    public Orientation getOrientation() {
        return getMower().getOrientation();
    }

}
