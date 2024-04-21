package com.omarfendri.kata.mowitnow.application;

import com.omarfendri.kata.mowitnow.application.port.out.MowerStateDisplayPort;
import com.omarfendri.kata.mowitnow.domain.model.mower.AutonomousMower;
import com.omarfendri.kata.mowitnow.domain.model.grid.Grid;
import com.omarfendri.kata.mowitnow.domain.model.mower.Mower;
import com.omarfendri.kata.mowitnow.domain.service.SimulationService;

import java.util.List;

public class SimulationUseCase {
    private final SimulationService simulationService;
    private final MowerStateDisplayPort displayPort;

    public SimulationUseCase(SimulationService simulationService, MowerStateDisplayPort displayPort) {
        this.simulationService = simulationService;
        this.displayPort = displayPort;
    }

    public String startSimulation(Grid grid, List<AutonomousMower> autonomousMowerList) {
        StringBuilder stringBuilder = new StringBuilder();
        for(AutonomousMower autonomousMower : autonomousMowerList) {
            Mower finalState = simulationService.simulateAutonomousMower(autonomousMower, grid).getMower();
            String aDisplayedState = displayPort.display(finalState);
            stringBuilder.append(aDisplayedState);
        }
        return stringBuilder.toString().trim();
    }
}
