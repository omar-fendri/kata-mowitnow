package com.omarfendri.kata.mowitnow.domain;

import com.omarfendri.kata.mowitnow.domain.enums.Instruction;
import com.omarfendri.kata.mowitnow.domain.enums.Orientation;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
@Getter
@SuperBuilder
public class AutonomousMower extends Mower {
    List<Instruction> instructionList;
    public AutonomousMower(Coordinate coordinate, Orientation orientation) {
        super(coordinate, orientation);
        instructionList = new ArrayList<>();
    }
    public void addAction(Instruction instruction) {
        instructionList.add(instruction);
    }

}
