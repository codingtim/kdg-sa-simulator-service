package be.codingtim.velo.simulator.service.sensor.generator.sensor;

public interface SensorConfiguration {

    String getType();

    int getLowerBound();

    int getUpperBoundExclusive();
}
