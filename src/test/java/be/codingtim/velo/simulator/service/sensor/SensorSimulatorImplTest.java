package be.codingtim.velo.simulator.service.sensor;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensorSimulatorImplTest {
    private final SensorValueReceiver discardSensorValue = value -> {
    };
    private final DelayAction noDelayAction = delay -> {
    };
    private final SensorSimulatorImpl sensorSimulator = new SensorSimulatorImpl(1,
            discardSensorValue, noDelayAction, new Random());

    @Test
    void runSimulations() throws InterruptedException {
        sensorSimulator.addSimulation(new DummySensorSimulationConfiguration());
        sensorSimulator.addSimulation(new DummySensorSimulationConfiguration());

        //await simulation termination
        Thread.sleep(200);

        SensorSimulatorSnapshot snapshot = sensorSimulator.getSnapshot();
        assertEquals(0, snapshot.waitingSimulations.size());
        assertEquals(0, snapshot.runningSimulations.size());
        assertEquals(2, snapshot.completedSimulations.size());
    }
}