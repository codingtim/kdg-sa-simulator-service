package be.codingtim.velo.simulator.service.sensor.generator.location;

public class DummyCoordinateConfiguration implements CoordinateConfiguration {

    private final double lowerBound;
    private final double upperBoundExclusive;

    public DummyCoordinateConfiguration(double lowerBound, double upperBoundExclusive) {
        this.lowerBound = lowerBound;
        this.upperBoundExclusive = upperBoundExclusive;
    }

    @Override
    public double getLowerBound() {
        return lowerBound;
    }

    @Override
    public double getUpperBoundExclusive() {
        return upperBoundExclusive;
    }
}
