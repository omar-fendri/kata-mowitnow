package com.omarfendri.kata.mowitnow.infrastructure.adapter.in;

import com.omarfendri.kata.mowitnow.application.usecase.SimulationUseCase;
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

    public final static String FILE_ERROR_MSG = "Failed to read the input file";
    public static final String CHECK_FILE_FORMAT_MSG = "Please check file format";
    public static final String SIMULATION_ERROR_MSG = "An error occurred during simulation";
    public FileAdapter(SimulationUseCase simulationUseCase) {
        this.simulationUseCase = simulationUseCase;
    }
    public String runSimulationFromFile(String filePath) {
        try {
            return run(filePath);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(CHECK_FILE_FORMAT_MSG);
            return CHECK_FILE_FORMAT_MSG;
        } catch (IOException e) {
            System.out.println(FILE_ERROR_MSG + e.getMessage());
            return FILE_ERROR_MSG;
        } catch (Exception e) {
            System.out.println(SIMULATION_ERROR_MSG + e.getMessage());
            return SIMULATION_ERROR_MSG;
        }
    }
    private String run(String filePath) throws IOException {
        List<String> lines = readLinesFromFile(filePath);
        Grid grid = parseGrid(lines.getFirst());
        List<AutonomousMower> autonomousMowerList = parseMowers(lines);
        return simulationUseCase.startSimulation(grid, autonomousMowerList);
    }

    private List<String> readLinesFromFile(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }

    private Grid parseGrid(String gridLine) {
        String[] gridDimensions = gridLine.split(" ");
        return Grid.builder()
                .width(Integer.parseInt(gridDimensions[0]))
                .height(Integer.parseInt(gridDimensions[1]))
                .build();
    }

    private List<AutonomousMower> parseMowers(List<String> lines) {
        List<AutonomousMower> mowers = new ArrayList<>();
        for (int i = 1; i < lines.size(); i += 2) {
            String positionAndOrientation = lines.get(i);
            String instructions = i + 1 < lines.size() ? lines.get(i + 1) : "";
            AutonomousMower mower = MowerFactory.fromString(positionAndOrientation, instructions);
            mowers.add(mower);
        }
        return mowers;
    }
}
