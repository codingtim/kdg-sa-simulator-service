package be.codingtim.velo.simulator.service.sensor;

import be.codingtim.velo.simulator.service.sensor.generator.location.Location;
import be.codingtim.velo.simulator.service.sensor.generator.location.LocationGenerator;
import be.codingtim.velo.simulator.service.sensor.generator.sensor.SensorReading;
import be.codingtim.velo.simulator.service.sensor.generator.sensor.SensorReadingGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * Single {@link SensorSimulator} which takes a configuration and generates {@link SensorValue}s accordingly.
 */
class SensorSimulation {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorSimulation.class);

    private final SensorValueReceiver sensorValueReceiver;
    private final DelayAction delayAction;
    private final SensorSimulationListener sensorSimulationListener;

    private final SensorReadingGenerator sensorReadingGenerator;
    private final LocationGenerator locationGenerator;
    private final DelayGenerator delayGenerator;
    private final Duration simulationDuration;

    SensorSimulation(SensorSimulationConfiguration configuration,
                            SensorValueReceiver sensorValueReceiver, DelayAction delayAction,
                            Random random, SensorSimulationListener sensorSimulationListener) {

        this.sensorValueReceiver = sensorValueReceiver;
        this.delayAction = delayAction;
        simulationDuration = configuration.getSimulationDuration();
        this.sensorSimulationListener = sensorSimulationListener;
        sensorReadingGenerator = new SensorReadingGenerator(configuration.getSensorConfigurations(), random);
        locationGenerator = new LocationGenerator(configuration.getLocationConfiguration(), random);
        delayGenerator = new DelayGenerator(configuration.getDelay(), configuration.getDelayVariation(), random);
    }

    void run(Instant startTime) {
        Instant currentTime = startTime;
        Instant endTime = startTime.plus(simulationDuration);
        int numberOfEventsCreated = 0;
        LOGGER.info("Started simulation run at {}", startTime);
        try {
            while (currentTime.isBefore(endTime)) {
                SensorReading sensorReading = sensorReadingGenerator.randomSensorReading();
                Location location = locationGenerator.randomLocation();
                SensorValue sensorValue = new SensorValue(currentTime, sensorReading.getType(), sensorReading.getValue(),
                        location.getLatitude(), location.getLongitude());
                numberOfEventsCreated++;
                sensorValueReceiver.receive(sensorValue);
                int delay = delayGenerator.randomDelay();
                delayAction.delay(delay);
                currentTime = currentTime.plusMillis(delay);
            }
            LOGGER.info("Ended simulation run at {}", endTime);
            sensorSimulationListener.simulationCompleted(new SensorSimulationResult(
                    true, numberOfEventsCreated, null, startTime, endTime
            ));
        } catch (Exception e) {
            LOGGER.error("Simulation crashed", e);
            sensorSimulationListener.simulationCompleted(new SensorSimulationResult(
                    false, numberOfEventsCreated, e.getMessage(), startTime, endTime
            ));
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
