package be.codingtim.velo.simulator.service.domain.sensor.generator.sensor;

public class DummySensorConfiguration implements SensorConfiguration {

    private final String type;
    private final int lowerBound;
    private final int upperBoundExclusive;

    public DummySensorConfiguration(String type, int lowerBound, int upperBoundExclusive) {
        this.type = type;
        this.lowerBound = lowerBound;
        this.upperBoundExclusive = upperBoundExclusive;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getLowerBound() {
        return lowerBound;
    }

    @Override
    public int getUpperBoundExclusive() {
        return upperBoundExclusive;
    }
}
