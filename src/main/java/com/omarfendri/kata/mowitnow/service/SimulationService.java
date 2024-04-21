package com.omarfendri.kata.mowitnow.service;

import com.omarfendri.kata.mowitnow.domain.AutonomousMower;
import com.omarfendri.kata.mowitnow.domain.Position;
import com.omarfendri.kata.mowitnow.domain.Grid;
import com.omarfendri.kata.mowitnow.domain.Mower;
import com.omarfendri.kata.mowitnow.domain.enums.Instruction;
import com.omarfendri.kata.mowitnow.domain.enums.Orientation;

public class SimulationService {
    public Mower applyInstruction(Instruction instruction, Mower mower, Grid grid) {
        switch (instruction) {
            case RIGHT:
                return Mower.builder()
                        .position(mower.getPosition())
                        .orientation(turnRight(mower))
                        .build();
            case LEFT:
                return Mower.builder()
                        .position(mower.getPosition())
                        .orientation(turnLeft(mower))
                        .build();
            case FORWARD:
                if (isValidPosition(calculatePositionWhenMovingForward(mower), grid)) {
                    return Mower.builder()
                            .position(calculatePositionWhenMovingForward(mower))
                            .orientation(mower.getOrientation())
                            .build();
                }
            default:
                return mower; // keep same state
        }
    }

    public AutonomousMower simulateAutonomousMower(AutonomousMower mower, Grid grid) {
       /* Mower finalMowerState = mower.getInstructionList().stream()
                // Start with the initial mower state and apply each instruction sequentially
                .reduce(mower, (currentMower, instruction) -> applyInstruction(instruction, currentMower, grid), (m1, m2) -> m2);

        // Build and return a new AutonomousMower with the final state
        return AutonomousMower.builder()
                .position(finalMowerState.getPosition())
                .orientation(finalMowerState.getOrientation())
                .instructionList(mower.getInstructionList())  // Optionally reassign the instruction list if needed
                .build(); */
        return null;
    }

    private static Position calculatePositionWhenMovingForward(Mower mower) {
        return switch(mower.getOrientation()) {
            case NORTH -> Position.builder()
                    .x(mower.getPosition().getX())
                    .y(mower.getPosition().getY() + 1)
                    .build();
            case EAST -> Position.builder()
                    .x(mower.getPosition().getX() + 1)
                    .y(mower.getPosition().getY())
                    .build();
            case SOUTH -> Position.builder()
                    .x(mower.getPosition().getX())
                    .y(mower.getPosition().getY() - 1)
                    .build();
            case WEST -> Position.builder()
                    .x(mower.getPosition().getX() - 1)
                    .y(mower.getPosition().getY())
                    .build();
        };
    }

    private Orientation turnLeft(Mower mower) {
        return switch (mower.getOrientation()) {
            case NORTH -> Orientation.WEST;
            case WEST -> Orientation.SOUTH;
            case SOUTH -> Orientation.EAST;
            case EAST -> Orientation.NORTH;
        };
    }

    private Orientation turnRight(Mower mower) {
        return switch (mower.getOrientation()) {
            case NORTH -> Orientation.EAST;
            case EAST -> Orientation.SOUTH;
            case SOUTH -> Orientation.WEST;
            case WEST -> Orientation.NORTH;
        };
    }

    private boolean isValidPosition(Position newPosition, Grid grid) {
        return newPosition.getX() >= 0 &&
                newPosition.getY() >= 0 &&
                newPosition.getX() <= grid.getWidth() &&
                newPosition.getY() <= grid.getHeight();
    }

}
