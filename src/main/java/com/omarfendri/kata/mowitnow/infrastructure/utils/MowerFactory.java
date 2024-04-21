package com.omarfendri.kata.mowitnow.infrastructure.utils;

import com.omarfendri.kata.mowitnow.domain.model.mower.AutonomousMower;
import com.omarfendri.kata.mowitnow.domain.model.mower.Mower;
import com.omarfendri.kata.mowitnow.domain.model.mower.Position;
import com.omarfendri.kata.mowitnow.domain.model.mower.Instruction;
import com.omarfendri.kata.mowitnow.domain.model.mower.Orientation;

import java.util.ArrayList;
import java.util.List;

public class MowerFactory {
    public static Mower fromString(String coordinateAndOrientationLine) {
        String[] parts = coordinateAndOrientationLine.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Orientation orientation = Orientation.getFromChar(parts[2]);
        return Mower.builder()
                .orientation(orientation)
                .position(Position.builder().x(x).y(y).build())
                .build();
    }

    public static AutonomousMower fromString(String coordinatesAndOrientationLine, String instructionsLine) {
        List<Instruction> instructions = new ArrayList<>();
        for (char instruction : instructionsLine.toCharArray()) {
            instructions.add(Instruction.getFromChar(instruction));
        }
        return AutonomousMower.builder()
                .mower(fromString(coordinatesAndOrientationLine))
                .instructionsList(instructions)
                .build();
    }
}
