package be.codingtim.velo.simulator.service.sensor.generator.location;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CoordinateGeneratorTest {

    private final Random random = new Random();
    private final double lowerBound = 10;
    private final double upperBound = 100;
    private final CoordinateGenerator coordinateGenerator = new CoordinateGenerator(lowerBound, upperBound, random);

    @RepeatedTest(20)
    void generateRandomValue() {
        double coordinate = coordinateGenerator.randomCoordinate();
        assertTrue(lowerBound <= coordinate);
        assertTrue(upperBound > coordinate);
    }
}