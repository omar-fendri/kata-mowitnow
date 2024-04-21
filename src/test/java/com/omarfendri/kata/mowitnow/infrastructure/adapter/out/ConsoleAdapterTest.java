package com.omarfendri.kata.mowitnow.infrastructure.adapter.out;

import com.omarfendri.kata.mowitnow.infrastructure.utils.MowerFactory;
import com.omarfendri.kata.mowitnow.domain.model.mower.Mower;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleAdapterTest {
    private final ConsoleAdapter consoleAdapter = new ConsoleAdapter();

    @Test
    public void outputIsCorrectlyDisplayed(){
        // Given
        Mower mower = MowerFactory.fromString("4 5 N");
        // When
        String output = consoleAdapter.display(mower);
        // Then
        assertThat(output).isEqualTo("4 5 N\n");
    }
}
