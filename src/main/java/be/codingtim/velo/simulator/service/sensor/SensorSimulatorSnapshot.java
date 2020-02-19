package be.codingtim.velo.simulator.service.sensor;

import java.util.List;

public class SensorSimulatorSnapshot {
    public final List<SensorSimulationView> waitingSimulations;
    public final List<SensorSimulationView> runningSimulations;
    public final List<SensorSimulationView> completedSimulations;

    public SensorSimulatorSnapshot(List<SensorSimulationView> waitingSimulations,
                                   List<SensorSimulationView> runningSimulations,
                                   List<SensorSimulationView> completedSimulations) {
        this.waitingSimulations = waitingSimulations;
        this.runningSimulations = runningSimulations;
        this.completedSimulations = completedSimulations;
    }
}
