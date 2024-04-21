package com.omarfendri.kata.mowitnow.service;

import com.omarfendri.kata.mowitnow.domain.Coordinate;
import com.omarfendri.kata.mowitnow.domain.Grid;
import com.omarfendri.kata.mowitnow.domain.Mower;
import com.omarfendri.kata.mowitnow.domain.enums.Instruction;
import com.omarfendri.kata.mowitnow.domain.enums.Orientation;

public class SimulationService {
    public Mower applyInstruction(Instruction instruction, Mower mower, Grid grid) {
        switch (instruction) {
            case RIGHT:
                return Mower.builder()
                        .coordinate(mower.getCoordinate())
                        .orientation(turnRight(mower))
                        .build();
            case LEFT:
                return Mower.builder()
                        .coordinate(mower.getCoordinate())
                        .orientation(turnLeft(mower))
                        .build();
            case FORWARD:
                if (isValidCoordinate(calculateCoordinateWhenMovingForward(mower), grid)) {
                    return Mower.builder()
                            .coordinate(calculateCoordinateWhenMovingForward(mower))
                            .orientation(mower.getOrientation())
                            .build();
                }
            default:
                return mower; // keep same state
        }
    }

    private static Coordinate calculateCoordinateWhenMovingForward(Mower mower) {
        return switch(mower.getOrientation()) {
            case NORTH -> Coordinate.builder()
                    .x(mower.getCoordinate().getX())
                    .y(mower.getCoordinate().getY() + 1)
                    .build();
            case EAST -> Coordinate.builder()
                    .x(mower.getCoordinate().getX() + 1)
                    .y(mower.getCoordinate().getY())
                    .build();
            case SOUTH -> Coordinate.builder()
                    .x(mower.getCoordinate().getX())
                    .y(mower.getCoordinate().getY() - 1)
                    .build();
            case WEST -> Coordinate.builder()
                    .x(mower.getCoordinate().getX() - 1)
                    .y(mower.getCoordinate().getY())
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

    private boolean isValidCoordinate(Coordinate newCoordinate, Grid grid) {
        return newCoordinate.getX() >= 0 &&
                newCoordinate.getY() >= 0 &&
                newCoordinate.getX() <= grid.getWidth() &&
                newCoordinate.getY() <= grid.getHeight();
    }

}
