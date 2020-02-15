package be.codingtim.velo.simulator.service.sensor.generator.sensor;

import java.util.Random;

class SensorValueGenerator {

    private final int lowerBound;
    private final int range;
    private final Random random;

    public SensorValueGenerator(int lowerBound, int upperBoundExclusive, Random random) {
        this.lowerBound = lowerBound;
        this.range = upperBoundExclusive - lowerBound;
        this.random = random;
    }

    public int randomValue() {
        return random.nextInt(range) + lowerBound;
    }
}
