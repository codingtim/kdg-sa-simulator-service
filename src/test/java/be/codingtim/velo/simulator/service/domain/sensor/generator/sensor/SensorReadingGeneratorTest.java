package be.codingtim.velo.simulator.service.domain.sensor.generator.sensor;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SensorReadingGeneratorTest {

    private final List<SensorConfiguration> sensorConfigurations = List.of(
            new DummySensorConfiguration("CO2", 10, 20),
            new DummySensorConfiguration("NOx", 50, 75)
    );
    private final SensorReadingGenerator generator = new SensorReadingGenerator(sensorConfigurations, new Random());

    @RepeatedTest(20)
    void randomSensorReading() {
        SensorReading sensorReading = generator.randomSensorReading();
        SensorConfiguration config = getConfigFor(sensorReading.getType());
        assertTrue(sensorReading.getValue() >= config.getLowerBound());
        assertTrue(sensorReading.getValue() < config.getUpperBoundExclusive());
    }

    private SensorConfiguration getConfigFor(String type) {
        for (SensorConfiguration sensorConfiguration : sensorConfigurations) {
            if (sensorConfiguration.getType().equals(type)) {
                return sensorConfiguration;
            }
        }
        throw new IllegalStateException("No configuration found for type: " + type);
    }

}