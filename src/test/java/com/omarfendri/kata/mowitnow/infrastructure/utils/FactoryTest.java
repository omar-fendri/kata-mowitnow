package com.omarfendri.kata.mowitnow.infrastructure.utils;

import com.omarfendri.kata.mowitnow.domain.model.grid.Grid;
import com.omarfendri.kata.mowitnow.domain.model.mower.AutonomousMower;
import com.omarfendri.kata.mowitnow.domain.model.mower.Instruction;
import com.omarfendri.kata.mowitnow.domain.model.mower.Mower;
import com.omarfendri.kata.mowitnow.domain.model.mower.Orientation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryTest {
    @Test
    public void gridIsCorrectlyInitializedFromString(){
        // When
        Grid grid = GridFactory.fromString("5 6");
        // Then
        assertThat(grid.getWidth()).isEqualTo(5);
        assertThat(grid.getHeight()).isEqualTo(6);
    }
    @Test
    public void autonomousMowerIsCorrectlyInitializedFromString() {
        // When
        AutonomousMower autonomousMower = MowerFactory
                .fromString("0 0 N", "ADAAGA");
        // Then
        assertThat(autonomousMower.getX()).isEqualTo(0);
        assertThat(autonomousMower.getY()).isEqualTo(0);
        assertThat(autonomousMower.getOrientation()).isEqualTo(Orientation.NORTH);
        assertThat(autonomousMower.getInstructionsList()).containsExactly(Instruction.FORWARD,
                Instruction.RIGHT, Instruction.FORWARD, Instruction.FORWARD, Instruction.LEFT, Instruction.FORWARD);
    }
    @Test
    public void mowerIsCorrectlyInitializedFromString() {
        // When
        Mower mower = MowerFactory.fromString("3 5 N");
        // Then
        assertThat(mower.getX()).isEqualTo(3);
        assertThat(mower.getY()).isEqualTo(5);
        assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
    }
}
