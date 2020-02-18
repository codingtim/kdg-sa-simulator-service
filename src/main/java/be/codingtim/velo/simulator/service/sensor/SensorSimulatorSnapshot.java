package be.codingtim.velo.simulator.service.sensor;

import java.util.List;

public class SensorSimulatorSnapshot {
    public final List<SensorSimulation> waitingSimulations;
    public final List<SensorSimulation> runningSimulations;
    public final List<SensorSimulation> completedSimulations;

    public SensorSimulatorSnapshot(List<SensorSimulation> waitingSimulations,
                                   List<SensorSimulation> runningSimulations,
                                   List<SensorSimulation> completedSimulations) {
        this.waitingSimulations = waitingSimulations;
        this.runningSimulations = runningSimulations;
        this.completedSimulations = completedSimulations;
    }
}
