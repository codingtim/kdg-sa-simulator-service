package be.codingtim.velo.simulator.service.domain.sensor;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class SensorSimulatorSnapshot {
    public final List<SensorSimulationView> waitingSimulations;
    public final List<SensorSimulationView> runningSimulations;
    public final List<SensorSimulationView> completedSimulations;

    public SensorSimulatorSnapshot(List<SensorSimulationView> waitingSimulations,
                                   List<SensorSimulationView> runningSimulations,
                                   List<SensorSimulationView> completedSimulations) {
        this.waitingSimulations = unmodifiableList(waitingSimulations);
        this.runningSimulations = unmodifiableList(runningSimulations);
        this.completedSimulations = unmodifiableList(completedSimulations);
    }
}
