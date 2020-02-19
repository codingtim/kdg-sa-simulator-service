package be.codingtim.velo.simulator.service.sensor;

import be.codingtim.velo.simulator.service.sensor.delay.DelayType;
import be.codingtim.velo.simulator.service.sensor.generator.location.LocationConfiguration;
import be.codingtim.velo.simulator.service.sensor.generator.sensor.SensorConfiguration;

import java.time.Duration;
import java.util.List;

public interface SensorSimulationConfiguration {

    Duration getSimulationDuration();

    int getDelay();

    int getDelayVariation();

    DelayType getDelayType();

    LocationConfiguration getLocationConfiguration();

    List<? extends SensorConfiguration> getSensorConfigurations();
}
