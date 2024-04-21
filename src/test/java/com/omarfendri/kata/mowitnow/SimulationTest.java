package com.omarfendri.kata.mowitnow;

import com.omarfendri.kata.mowitnow.domain.Coordinate;
import com.omarfendri.kata.mowitnow.domain.Grid;
import com.omarfendri.kata.mowitnow.domain.Mower;
import com.omarfendri.kata.mowitnow.domain.enums.Instruction;
import com.omarfendri.kata.mowitnow.domain.enums.Orientation;
import com.omarfendri.kata.mowitnow.service.SimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulationTest {
    private SimulationService simulationService;
    private Grid grid;

    @BeforeEach
    public void setup(){
        simulationService = new SimulationService();
        grid = Grid.builder().height(7).width(7).build();
    }

    @Test
    public void mowerMovesForwardWhenFacingNorth() {
        // Given
        Mower mower = Mower.builder()
                .orientation(Orientation.NORTH)
                .coordinate(Coordinate.builder()
                        .x(3).y(5).build())
                .build();

        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getCoordinate().getX()).isEqualTo(3);
        assertThat(simulatedMower.getCoordinate().getY()).isEqualTo(6);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.NORTH); // Should keep orientation
    }

    @Test
    public void mowerMovesForwardWhenFacingSouth() {
        // Given
        Mower mower = Mower.builder()
                .orientation(Orientation.SOUTH)
                .coordinate(Coordinate.builder()
                        .x(3).y(5).build())
                .build();
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getCoordinate().getX()).isEqualTo(3);
        assertThat(simulatedMower.getCoordinate().getY()).isEqualTo(4);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.SOUTH);
    }

    @Test
    public void mowerMovesForwardWhenFacingEast() {
        // Given
        Mower mower = Mower.builder()
                .orientation(Orientation.EAST)
                .coordinate(Coordinate.builder()
                        .x(3).y(5).build())
                .build();
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getCoordinate().getX()).isEqualTo(4);
        assertThat(simulatedMower.getCoordinate().getY()).isEqualTo(5);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    public void mowerMovesForwardWhenFacingWest() {
        // Given
        Mower mower = Mower.builder()
                .orientation(Orientation.WEST)
                .coordinate(Coordinate.builder()
                        .x(3).y(5).build())
                .build();
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getCoordinate().getX()).isEqualTo(2);
        assertThat(simulatedMower.getCoordinate().getY()).isEqualTo(5);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.WEST);
    }

    @Test
    public void mowerTurnsLeftFromNorthToWest() {
        // Given
        Mower mower = Mower.builder()
                .orientation(Orientation.NORTH)
                .coordinate(Coordinate.builder().x(3).y(5).build())
                .build();
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.LEFT, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.WEST);
        assertThat(simulatedMower.getCoordinate().getX()).isEqualTo(3);
        assertThat(simulatedMower.getCoordinate().getY()).isEqualTo(5);
    }

    @Test
    public void mowerTurnsLeftFromWestToSouth() {
        // Given
        Mower mower = Mower.builder()
                .orientation(Orientation.WEST)
                .coordinate(Coordinate.builder().x(3).y(5).build())
                .build();
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.LEFT, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.SOUTH);
        assertThat(simulatedMower.getCoordinate().getX()).isEqualTo(3);
        assertThat(simulatedMower.getCoordinate().getY()).isEqualTo(5);
    }

    @Test
    public void mowerTurnsRightFromWestToNorth() {
        //Given
        Mower mower = Mower.builder()
                .orientation(Orientation.WEST)
                .coordinate(Coordinate.builder().x(3).y(5).build())
                .build();

        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.RIGHT, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.NORTH);
        assertThat(simulatedMower.getCoordinate().getX()).isEqualTo(3);
        assertThat(simulatedMower.getCoordinate().getY()).isEqualTo(5);
    }

    @Test
    public void mowerTurnsRightFromNorthToEast() {
        // Given
        Mower mower = Mower.builder()
                .orientation(Orientation.NORTH)
                .coordinate(Coordinate.builder().x(3).y(5).build())
                .build();
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.RIGHT, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.EAST);
        assertThat(simulatedMower.getCoordinate().getX()).isEqualTo(3);
        assertThat(simulatedMower.getCoordinate().getY()).isEqualTo(5);
    }

    @Test
    public void mowerDoesNotMoveBeyondGridEdgeWhenMovingForward() {
        // Given
        Mower mower = Mower.builder()
                .orientation(Orientation.NORTH)
                .coordinate(Coordinate.builder().x(3).y(7).build())
                .build();
        Grid grid = Grid.builder().height(7).width(7).build();
        // When
        SimulationService simulationService = new SimulationService();
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.NORTH);
        assertThat(simulatedMower.getCoordinate().getX()).isEqualTo(3);
        assertThat(simulatedMower.getCoordinate().getY()).isEqualTo(7); // Should not move beyond grid
    }

}
