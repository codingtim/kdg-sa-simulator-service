package be.codingtim.velo.simulator.service.domain.sensor.generator.sensor;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SensorValueGeneratorTest {

    private final Random random = new Random();
    private final int lowerBound = 10;
    private final int upperBound = 100;
    private final SensorValueGenerator sensorValueGenerator = new SensorValueGenerator(lowerBound, upperBound, random);

    @RepeatedTest(20)
    void generateRandomValue() {
        int value = sensorValueGenerator.randomValue();
        assertTrue(value >= lowerBound);
        assertTrue(value < upperBound);
    }
}