package com.omarfendri.kata.mowitnow.service;

import com.omarfendri.kata.mowitnow.domain.AutonomousMower;
import com.omarfendri.kata.mowitnow.domain.Mower;
import com.omarfendri.kata.mowitnow.domain.Position;
import com.omarfendri.kata.mowitnow.domain.enums.Instruction;
import com.omarfendri.kata.mowitnow.domain.enums.Orientation;

import java.util.ArrayList;
import java.util.List;

public class MowerService {
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
