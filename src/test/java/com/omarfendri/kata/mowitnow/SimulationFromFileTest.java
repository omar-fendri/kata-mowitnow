package com.omarfendri.kata.mowitnow;

import com.omarfendri.kata.mowitnow.application.usecase.SimulationUseCase;
import com.omarfendri.kata.mowitnow.application.port.out.MowerStateDisplayPort;
import com.omarfendri.kata.mowitnow.domain.service.SimulationService;
import com.omarfendri.kata.mowitnow.infrastructure.adapter.in.FileAdapter;
import com.omarfendri.kata.mowitnow.infrastructure.adapter.out.ConsoleAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class SimulationFromFileTest {
    private FileAdapter fileAdapter;

    @BeforeEach
    void setup(){
        SimulationService simulationService = new SimulationService();
        MowerStateDisplayPort mowerStateDisplayPort = new ConsoleAdapter();
        SimulationUseCase simulationUseCase = new SimulationUseCase(simulationService, mowerStateDisplayPort);
        fileAdapter = new FileAdapter(simulationUseCase);
    }

    @Test
    void testRunSimulationFromFile() throws IOException, URISyntaxException {
        // Given
        ClassLoader classLoader = getClass().getClassLoader();
        Path filePath = Paths.get(Objects.requireNonNull(classLoader.getResource("mower_input.txt")).toURI());
        // When
        String result = fileAdapter.runSimulationFromFile(filePath.toString());
        // Then
        assertThat(result).isEqualTo("1 3 N\n5 1 E");
    }

    @Test
    void testGridLineIsMissingParameter() throws IOException, URISyntaxException {
        // Given
        ClassLoader classLoader = getClass().getClassLoader();
        Path filePath = Paths.get(Objects.requireNonNull(classLoader.getResource("mower_error.txt")).toURI());
        // When
        String result = fileAdapter.runSimulationFromFile(filePath.toString());
        // Then
        assertThat(result).isEqualTo(FileAdapter.CHECK_FILE_FORMAT_MSG);
    }

    @Test
    void testGridLineIsMissingParamAeter() throws IOException, URISyntaxException {
        // Given
        ClassLoader classLoader = getClass().getClassLoader();
        Path filePath = Paths.get(Objects.requireNonNull(classLoader.getResource("mower_error2.txt")).toURI());
        // When
        String result = fileAdapter.runSimulationFromFile(filePath.toString());
        // Then
        assertThat(result).isEqualTo(FileAdapter.CHECK_FILE_FORMAT_MSG);
    }


}
