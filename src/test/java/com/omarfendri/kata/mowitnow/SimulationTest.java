package com.omarfendri.kata.mowitnow;

import com.omarfendri.kata.mowitnow.domain.Position;
import com.omarfendri.kata.mowitnow.domain.Grid;
import com.omarfendri.kata.mowitnow.domain.Mower;
import com.omarfendri.kata.mowitnow.domain.enums.Instruction;
import com.omarfendri.kata.mowitnow.domain.enums.Orientation;
import com.omarfendri.kata.mowitnow.service.MowerService;
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
    public void mowerIsCorrectlyInitializedFromString() {
        // When
        Mower mower = MowerService.fromString("3 5 N");
        // Then
        assertThat(mower.getX()).isEqualTo(3);
        assertThat(mower.getY()).isEqualTo(5);
        assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
    }

    @Test
    public void mowerMovesForwardWhenFacingNorth() {
        // Given
        Mower mower = MowerService.fromString("3 5 N");
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
        Mower mower = MowerService.fromString("3 5 S");
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
        Mower mower = MowerService.fromString("3 5 E");
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
        Mower mower = MowerService.fromString("3 5 W");
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
        Mower mower = MowerService.fromString("3 5 N");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.LEFT, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.WEST);
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(5);
    }

    @Test
    public void mowerTurnsLeftFromWestToSouth() {
        // Given
        Mower mower = MowerService.fromString("3 5 W");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.LEFT, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.SOUTH);
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(5);
    }

    @Test
    public void mowerTurnsRightFromWestToNorth() {
        //Given
        Mower mower = MowerService.fromString("3 5 W");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.RIGHT, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.NORTH);
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(5);
    }

    @Test
    public void mowerTurnsRightFromNorthToEast() {
        // Given
        Mower mower = MowerService.fromString("3 5 N");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.RIGHT, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.EAST);
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(5);
    }

    @Test
    public void mowerDoesNotMoveBeyondGridEdgeWhenMovingForward() {
        // Given
        Mower mower = MowerService.fromString("3 7 N");
        // When
        Mower simulatedMower = simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        // Then
        assertThat(simulatedMower.getOrientation()).isEqualTo(Orientation.NORTH);
        assertThat(simulatedMower.getX()).isEqualTo(3);
        assertThat(simulatedMower.getY()).isEqualTo(7); // Should not move beyond grid
    }

}
