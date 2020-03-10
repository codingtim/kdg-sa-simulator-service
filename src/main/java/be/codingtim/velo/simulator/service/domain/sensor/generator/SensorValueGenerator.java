package be.codingtim.velo.simulator.service.domain.sensor.generator;

import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulationConfiguration;
import be.codingtim.velo.simulator.service.domain.sensor.SensorValue;
import be.codingtim.velo.simulator.service.domain.sensor.generator.location.Location;
import be.codingtim.velo.simulator.service.domain.sensor.generator.location.LocationGenerator;
import be.codingtim.velo.simulator.service.domain.sensor.generator.sensor.SensorReading;
import be.codingtim.velo.simulator.service.domain.sensor.generator.sensor.SensorReadingGenerator;

import java.time.Instant;
import java.util.Random;

public class SensorValueGenerator {
    private final SensorReadingGenerator sensorReadingGenerator;
    private final LocationGenerator locationGenerator;

    public SensorValueGenerator(SensorSimulationConfiguration configuration, Random random) {
        sensorReadingGenerator = new SensorReadingGenerator(configuration.getSensorConfigurations(), random);
        locationGenerator = new LocationGenerator(configuration.getLocationConfiguration(), random);
    }

    public SensorValue randomSensorValue(Instant instant) {
        SensorReading sensorReading = sensorReadingGenerator.randomSensorReading();
        Location location = locationGenerator.randomLocation();
        return new SensorValue(instant, sensorReading.getType(), sensorReading.getValue(),
                location.getLatitude(), location.getLongitude());
    }
}
