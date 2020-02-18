package be.codingtim.velo.simulator.service.sensor;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SensorSimulationTest {

    private final SensorValueReceiver discardSensorValue = value -> {
    };
    private final DelayAction noDelayAction = delay -> {
    };

    @Test
    void runSimulation() {
        KeepCompletedSimulation keepCompletedSimulation = new KeepCompletedSimulation();
        SensorSimulation sensorSimulation = new SensorSimulation(new DummySensorSimulationConfiguration(),
                discardSensorValue, noDelayAction, new Random(), keepCompletedSimulation);

        sensorSimulation.run(Instant.now());

        SensorSimulation completedSimulation = keepCompletedSimulation.result;
        assertNotNull(completedSimulation);
        assertTrue(completedSimulation.getResult().isPresent());
        SensorSimulationResult result = completedSimulation.getResult().get();
        assertTrue(result.isSuccess());
        assertTrue(result.getNumberOfEventsGenerated() > 0);
    }

    public static class KeepCompletedSimulation implements SensorSimulationListener {

        private SensorSimulation result;

        @Override
        public void simulationCompleted(SensorSimulation result) {
            this.result = result;
        }
    }

}