package com.omarfendri.kata.mowitnow;

import com.omarfendri.kata.mowitnow.application.SimulationUseCase;
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

public class SimulationFromFileTest {
    private FileAdapter fileAdapter;

    @BeforeEach
    public void setup(){
        SimulationService simulationService = new SimulationService();
        MowerStateDisplayPort mowerStateDisplayPort = new ConsoleAdapter();
        SimulationUseCase simulationUseCase = new SimulationUseCase(simulationService, mowerStateDisplayPort);
        fileAdapter = new FileAdapter(simulationUseCase);
    }

    @Test
    public void testRunSimulationFromFile() throws IOException, URISyntaxException {
        // Given
        ClassLoader classLoader = getClass().getClassLoader();
        Path filePath = Paths.get(Objects.requireNonNull(classLoader.getResource("mower_input.txt")).toURI());

        // When
        String result = fileAdapter.runSimulationFromFile(filePath.toString());

        // Then
        assertThat(result).isEqualTo("1 3 N\n5 1 E");

    }
}
