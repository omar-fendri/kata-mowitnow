package com.omarfendri.kata.mowitnow;

import com.omarfendri.kata.mowitnow.domain.Coordinate;
import com.omarfendri.kata.mowitnow.domain.Grid;
import com.omarfendri.kata.mowitnow.domain.Mower;
import com.omarfendri.kata.mowitnow.domain.enums.Instruction;
import com.omarfendri.kata.mowitnow.domain.enums.Orientation;
import com.omarfendri.kata.mowitnow.service.SimulationService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulationTest {
    @Test
    public void testMoveMowerForward() {
        // Given
        Mower mower = Mower.builder()
                .orientation(Orientation.NORTH)
                .coordinate(Coordinate.builder()
                        .x(3).y(5).build())
                .build();
        Grid grid = Grid.builder().length(7).width(7).build();
        // When
        SimulationService simulationService = new SimulationService();
        simulationService.applyInstruction(Instruction.FORWARD, mower, grid);
        Coordinate actualCoordinate = mower.getCoordinate();
        // Then
        assertThat(actualCoordinate.getX()).isEqualTo(3);
        assertThat(actualCoordinate.getY()).isEqualTo(6);
    }
}
