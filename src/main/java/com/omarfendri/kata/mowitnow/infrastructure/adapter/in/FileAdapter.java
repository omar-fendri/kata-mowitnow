package com.omarfendri.kata.mowitnow.infrastructure.adapter.in;

import com.omarfendri.kata.mowitnow.application.SimulationUseCase;
import com.omarfendri.kata.mowitnow.domain.model.grid.Grid;
import com.omarfendri.kata.mowitnow.domain.model.mower.AutonomousMower;
import com.omarfendri.kata.mowitnow.infrastructure.utils.MowerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileAdapter {
    private final SimulationUseCase simulationUseCase;

    public FileAdapter(SimulationUseCase simulationUseCase) {
        this.simulationUseCase = simulationUseCase;
    }

    public String runSimulationFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        String[] gridDimensions = lines.getFirst().split(" ");
        Grid grid = Grid.builder()
                .width(Integer.parseInt(gridDimensions[0]))
                .height(Integer.parseInt(gridDimensions[1]))
                .build();

        List<AutonomousMower> mowers = new ArrayList<>();
        for (int i = 1; i < lines.size(); i += 2) {
            String positionAndOrientation = lines.get(i);
            String instructions = lines.get(i + 1);
            AutonomousMower mower = MowerFactory.fromString(positionAndOrientation, instructions);
            mowers.add(mower);
        }
        return simulationUseCase.startSimulation(grid, mowers);
    }
}
