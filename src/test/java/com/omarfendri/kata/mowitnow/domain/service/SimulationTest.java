package com.omarfendri.kata.mowitnow.domain.service;

import com.omarfendri.kata.mowitnow.domain.model.mower.AutonomousMower;
import com.omarfendri.kata.mowitnow.domain.model.grid.Grid;
import com.omarfendri.kata.mowitnow.domain.model.mower.Mower;
import com.omarfendri.kata.mowitnow.domain.model.mower.Instruction;
import com.omarfendri.kata.mowitnow.domain.model.mower.Orientation;
import com.omarfendri.kata.mowitnow.infrastructure.utils.GridFactory;
import com.omarfendri.kata.mowitnow.infrastructure.utils.MowerFactory;
import com.omarfendri.kata.mowitnow.domain.service.SimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulationTest {
    private SimulationService simulationService;
    private Grid grid;

    @BeforeEach
    public void setup(){
        simulationService = new SimulationService();
        grid = GridFactory.fromString("7 7");
    }

    @Test
    public void mowerMovesForwardWhenFacingNorth() {
        // Given
        Mower mower = MowerFactory.fromString("3 5 N");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(6);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.NORTH); // Should keep orientation
    }

    @Test
    public void mowerMovesForwardWhenFacingSouth() {
        // Given
        Mower mower = MowerFactory.fromString("3 5 S");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(4);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.SOUTH);
    }

    @Test
    public void mowerMovesForwardWhenFacingEast() {
        // Given
        Mower mower = MowerFactory.fromString("3 5 E");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getX()).isEqualTo(4);
        assertThat(simulatedMower.getY()).isEqualTo(5);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    public void mowerMovesForwardWhenFacingWest() {
        // Given
        Mower mower = MowerFactory.fromString("3 5 W");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getX()).isEqualTo(2);
        assertThat(simulatedMower.getY()).isEqualTo(5);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.WEST);
    }

    @Test
    public void mowerTurnsLeftFromNorthToWest() {
        // Given
        Mower mower = MowerFactory.fromString("3 5 N");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.LEFT, mower, grid);
        // Then
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(5);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.WEST);
    }

    @Test
    public void mowerTurnsLeftFromWestToSouth() {
        // Given
        Mower mower = MowerFactory.fromString("3 5 W");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.LEFT, mower, grid);
        // Then
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(5);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.SOUTH);
    }

    @Test
    public void mowerTurnsRightFromWestToNorth() {
        //Given
        Mower mower = MowerFactory.fromString("3 5 W");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.RIGHT, mower, grid);
        // Then
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(5);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.NORTH);
    }

    @Test
    public void mowerTurnsRightFromNorthToEast() {
        // Given
        Mower mower = MowerFactory.fromString("3 5 N");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.RIGHT, mower, grid);
        // Then
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(5);
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.EAST);
    }

    @Test
    public void mowerDoesNotMoveBeyondGridEdgeWhenMovingForward() {
        // Given
        Mower mower = MowerFactory.fromString("3 7 N");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(7); // Should not move beyond grid
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.NORTH);
    }

    @Test
    public void autonomousMowerMovesToCorrectPosition() {
        // Given
        grid = GridFactory.fromString("5 5");
        AutonomousMower autonomousMower = MowerFactory
                .fromString("1 2 N", "GAGAGAGAA");
        // When
        AutonomousMower simulatedAutonomousMower = simulationService.simulateAutonomousMower(autonomousMower, grid);
        // Then
        assertThat(simulatedAutonomousMower.getX()).isEqualTo(1);
        assertThat(simulatedAutonomousMower.getY()).isEqualTo(3);
        assertThat(simulatedAutonomousMower.getOrientation()).isEqualTo(Orientation.NORTH);
    }
}
