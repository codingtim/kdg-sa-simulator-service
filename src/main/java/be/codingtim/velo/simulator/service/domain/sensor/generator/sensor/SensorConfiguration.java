package be.codingtim.velo.simulator.service.domain.sensor.generator.sensor;

public interface SensorConfiguration {

    String getType();

    int getLowerBound();

    int getUpperBoundExclusive();
}
