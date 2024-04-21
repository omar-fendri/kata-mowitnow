package com.omarfendri.kata.mowitnow.domain;

import com.omarfendri.kata.mowitnow.domain.enums.Instruction;
import com.omarfendri.kata.mowitnow.domain.enums.Orientation;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
@Getter
@SuperBuilder
public class AutonomousMower extends Mower {
    List<Instruction> instructionList;
    public AutonomousMower(Position position, Orientation orientation) {
        super(position, orientation);
        instructionList = new ArrayList<>();
    }
    public void addInstruction(Instruction instruction) {
        instructionList.add(instruction);
    }

}
