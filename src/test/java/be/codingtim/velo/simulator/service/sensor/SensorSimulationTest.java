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
        KeepSensorResult keepSensorResult = new KeepSensorResult();
        SensorSimulation sensorSimulation = new SensorSimulation(new DummySensorSimulationConfiguration(),
                discardSensorValue, noDelayAction, new Random(), keepSensorResult);

        sensorSimulation.run(Instant.now());

        SensorSimulationResult result = keepSensorResult.result;
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertTrue(result.getNumberOfEventsGenerated() > 0);
    }

    public static class KeepSensorResult implements SensorSimulationListener {

        private SensorSimulationResult result;

        @Override
        public void simulationCompleted(SensorSimulationResult result) {
            this.result = result;
        }
    }

}