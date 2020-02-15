package be.codingtim.velo.simulator.service.sensor.generator.location;

import java.util.Random;

class CoordinateGenerator {
    private final double lowerBound;
    private final double range;
    private final Random random;

    public CoordinateGenerator(CoordinateConfiguration latitudeConfiguration, Random random) {
        this(latitudeConfiguration.getLowerBound(), latitudeConfiguration.getUpperBoundExclusive(), random);
    }

    CoordinateGenerator(double lowerBound, double upperBoundExclusive, Random random) {
        this.lowerBound = lowerBound;
        this.range = upperBoundExclusive - lowerBound;
        this.random = random;
    }

    public double randomCoordinate() {
        return random.nextDouble() * range + lowerBound;
    }
}
