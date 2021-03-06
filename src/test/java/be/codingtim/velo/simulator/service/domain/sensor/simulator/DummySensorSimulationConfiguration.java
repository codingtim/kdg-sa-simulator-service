package be.codingtim.velo.simulator.service.domain.sensor.simulator;

import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulationConfiguration;
import be.codingtim.velo.simulator.service.domain.sensor.delay.DelayType;
import be.codingtim.velo.simulator.service.domain.sensor.generator.location.DummyCoordinateConfiguration;
import be.codingtim.velo.simulator.service.domain.sensor.generator.location.DummyLocationConfiguration;
import be.codingtim.velo.simulator.service.domain.sensor.generator.location.LocationConfiguration;
import be.codingtim.velo.simulator.service.domain.sensor.generator.sensor.DummySensorConfiguration;
import be.codingtim.velo.simulator.service.domain.sensor.generator.sensor.SensorConfiguration;

import java.time.Duration;
import java.util.List;

class DummySensorSimulationConfiguration implements SensorSimulationConfiguration {

    @Override
    public Duration getSimulationDuration() {
        return Duration.ofMinutes(15);
    }

    @Override
    public int getDelay() {
        return 2000;
    }

    @Override
    public int getDelayVariation() {
        return 200;
    }

    @Override
    public DelayType getDelayType() {
        return DelayType.NO_DELAY;
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return new DummyLocationConfiguration(
                new DummyCoordinateConfiguration(51.2012806, 51.2064643),
                new DummyCoordinateConfiguration(4.5827843, 4.6048775)
        );
    }

    @Override
    public List<SensorConfiguration> getSensorConfigurations() {
        return List.of(
                new DummySensorConfiguration("CO2", 0, 25),
                new DummySensorConfiguration("NOx", 0, 15)
        );
    }
}
