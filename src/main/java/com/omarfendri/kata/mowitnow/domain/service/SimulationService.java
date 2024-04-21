package com.omarfendri.kata.mowitnow.domain.service;

import com.omarfendri.kata.mowitnow.domain.model.mower.AutonomousMower;
import com.omarfendri.kata.mowitnow.domain.model.mower.Position;
import com.omarfendri.kata.mowitnow.domain.model.grid.Grid;
import com.omarfendri.kata.mowitnow.domain.model.mower.Mower;
import com.omarfendri.kata.mowitnow.domain.model.mower.Instruction;
import com.omarfendri.kata.mowitnow.domain.model.mower.Orientation;

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

    public AutonomousMower simulateAutonomousMower(AutonomousMower autonomousMower, Grid grid) {
       Mower finalMowerState = autonomousMower.getInstructionsList().stream()
                .reduce(autonomousMower.getMower(), (currentMower, instruction) -> applyInstruction(instruction, currentMower, grid), (m1, m2) -> m2);

        return AutonomousMower.builder()
                .mower(finalMowerState)
                .instructionsList(autonomousMower.getInstructionsList())
                .build();
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
