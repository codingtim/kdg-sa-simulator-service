package be.codingtim.velo.simulator.service.sensor;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensorSimulatorTest {
    private final SensorValueReceiver discardSensorValue = value -> {
    };
    private final DelayAction noDelayAction = delay -> {
    };
    private final SensorSimulator sensorSimulator = new SensorSimulator(1,
            discardSensorValue, noDelayAction, new Random());

    @Test
    void runSimulations() throws InterruptedException {
        sensorSimulator.addSimulation(new DummySensorSimulationConfiguration());
        sensorSimulator.addSimulation(new DummySensorSimulationConfiguration());

        //await simulation termination
        Thread.sleep(200);

        List<SensorSimulationResult> completedSensorSimulations = sensorSimulator.getCompletedSensorSimulations();
        assertEquals(2, completedSensorSimulations.size());
    }
}