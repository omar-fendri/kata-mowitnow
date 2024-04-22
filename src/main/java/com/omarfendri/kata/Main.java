package com.omarfendri.kata;

import com.omarfendri.kata.mowitnow.application.port.out.MowerStateDisplayPort;
import com.omarfendri.kata.mowitnow.application.usecase.SimulationUseCase;
import com.omarfendri.kata.mowitnow.domain.service.SimulationService;
import com.omarfendri.kata.mowitnow.infrastructure.adapter.in.FileAdapter;
import com.omarfendri.kata.mowitnow.infrastructure.adapter.out.ConsoleAdapter;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the path to the input file");
            return;
        }
        String filePath = args[0];
        SimulationService simulationService = new SimulationService();
        MowerStateDisplayPort mowerStateDisplayPort = new ConsoleAdapter();
        SimulationUseCase simulationUseCase = new SimulationUseCase(simulationService, mowerStateDisplayPort);
        FileAdapter fileAdapter = new FileAdapter(simulationUseCase);
        fileAdapter.runSimulationFromFile(filePath);
    }
}