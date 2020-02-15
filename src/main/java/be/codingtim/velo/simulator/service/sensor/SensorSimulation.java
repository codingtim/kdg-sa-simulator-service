package be.codingtim.velo.simulator.service.sensor;

import be.codingtim.velo.simulator.service.sensor.generator.location.Location;
import be.codingtim.velo.simulator.service.sensor.generator.location.LocationGenerator;
import be.codingtim.velo.simulator.service.sensor.generator.sensor.SensorReading;
import be.codingtim.velo.simulator.service.sensor.generator.sensor.SensorReadingGenerator;

import java.time.Instant;
import java.util.Random;

public class SensorSimulation {

    private final Instant startTime;
    private final Instant endTime;

    private final SensorValueReceiver sensorValueReceiver;
    private final DelayAction delayAction;

    private final SensorReadingGenerator sensorReadingGenerator;
    private final LocationGenerator locationGenerator;
    private final DelayGenerator delayGenerator;

    private Instant currentTime;

    public SensorSimulation(Instant startTime, SensorSimulationConfiguration configuration,
                            SensorValueReceiver sensorValueReceiver, DelayAction delayAction,
                            Random random) {
        this.startTime = startTime;
        this.currentTime = startTime;
        this.sensorValueReceiver = sensorValueReceiver;
        this.delayAction = delayAction;
        endTime = startTime.plus(configuration.getSimulationDuration());
        sensorReadingGenerator = new SensorReadingGenerator(configuration.getSensorConfigurations(), random);
        locationGenerator = new LocationGenerator(configuration.getLocationConfiguration(), random);
        delayGenerator = new DelayGenerator(configuration.getDelay(), configuration.getDelayVariation(), random);
    }

    public void run() {
        while (currentTime.isBefore(endTime)) {
            SensorReading sensorReading = sensorReadingGenerator.randomSensorReading();
            Location location = locationGenerator.randomLocation();
            SensorValue sensorValue = new SensorValue(currentTime, sensorReading.getType(), sensorReading.getValue(),
                    location.getLatitude(), location.getLongitude());
            sensorValueReceiver.receive(sensorValue);
            int delay = delayGenerator.randomDelay();
            delayAction.delay(delay);
            currentTime = currentTime.plusMillis(delay);
        }
    }

    private static class DelayGenerator {

        private final int delay;
        private final int delayVariation;
        private final Random random;

        public DelayGenerator(int delay, int delayVariation, Random random) {
            this.delay = delay;
            this.delayVariation = delayVariation;
            this.random = random;
        }

        int randomDelay() {
            return delay + random.nextInt(delayVariation * 2) - delayVariation;
        }
    }
}
