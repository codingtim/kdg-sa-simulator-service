package be.codingtim.velo.simulator.service.sensor;

public interface SensorSimulator {
    SensorSimulationView addSimulation(SensorSimulationConfiguration configuration);

    SensorSimulatorSnapshot getSnapshot();
}
