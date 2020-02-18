package be.codingtim.velo.simulator.service.sensor;

import be.codingtim.velo.simulator.service.sensor.generator.location.Location;
import be.codingtim.velo.simulator.service.sensor.generator.location.LocationGenerator;
import be.codingtim.velo.simulator.service.sensor.generator.sensor.SensorReading;
import be.codingtim.velo.simulator.service.sensor.generator.sensor.SensorReadingGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

/**
 * Single {@link SensorSimulatorImpl} which takes a configuration and generates {@link SensorValue}s accordingly.
 */
class SensorSimulation {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorSimulation.class);

    private final SensorValueReceiver sensorValueReceiver;
    private final DelayAction delayAction;
    private final SensorSimulationListener sensorSimulationListener;

    private final SensorReadingGenerator sensorReadingGenerator;
    private final LocationGenerator locationGenerator;
    private final DelayGenerator delayGenerator;

    private final SensorSimulationConfiguration configuration;
    private SensorSimulationResult result;

    SensorSimulation(SensorSimulationConfiguration configuration,
                     SensorValueReceiver sensorValueReceiver, DelayAction delayAction,
                     Random random, SensorSimulationListener sensorSimulationListener) {

        this.sensorValueReceiver = sensorValueReceiver;
        this.delayAction = delayAction;
        this.configuration = configuration;
        this.sensorSimulationListener = sensorSimulationListener;
        sensorReadingGenerator = new SensorReadingGenerator(configuration.getSensorConfigurations(), random);
        locationGenerator = new LocationGenerator(configuration.getLocationConfiguration(), random);
        delayGenerator = new DelayGenerator(configuration.getDelay(), configuration.getDelayVariation(), random);
    }

    void run(Instant startTime) {
        Instant currentTime = startTime;
        Instant endTime = startTime.plus(configuration.getSimulationDuration());
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
            this.result = new SensorSimulationResult(true, numberOfEventsCreated, null, startTime, endTime);
            sensorSimulationListener.simulationCompleted(this);
        } catch (Exception e) {
            LOGGER.error("Simulation crashed", e);
            this.result = new SensorSimulationResult(false, numberOfEventsCreated, e.getMessage(), startTime, endTime);
            sensorSimulationListener.simulationCompleted(this);
        }
    }

    public Optional<SensorSimulationResult> getResult() {
        return Optional.ofNullable(result);
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
