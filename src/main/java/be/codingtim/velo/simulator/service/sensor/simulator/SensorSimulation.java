package be.codingtim.velo.simulator.service.sensor.simulator;

import be.codingtim.velo.simulator.service.sensor.SensorSimulationConfiguration;
import be.codingtim.velo.simulator.service.sensor.SensorSimulationResult;
import be.codingtim.velo.simulator.service.sensor.SensorSimulationView;
import be.codingtim.velo.simulator.service.sensor.SensorValue;
import be.codingtim.velo.simulator.service.sensor.delay.DelayAction;
import be.codingtim.velo.simulator.service.sensor.generator.SensorValueGenerator;
import be.codingtim.velo.simulator.service.sensor.receiver.SensorValueReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

/**
 * Single {@link SensorSimulation} which takes a configuration and generates {@link SensorValue}s accordingly.
 */
class SensorSimulation implements SensorSimulationView {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorSimulation.class);

    private final SensorValueReceiver sensorValueReceiver;
    private final DelayAction delayAction;
    private final SensorSimulationListener sensorSimulationListener;

    private final SensorValueGenerator sensorValueGenerator;
    private final DelayGenerator delayGenerator;

    private final String id = UUID.randomUUID().toString();
    private final SensorSimulationConfiguration configuration;
    private SensorSimulationResult result;

    SensorSimulation(SensorSimulationConfiguration configuration,
                     SensorValueReceiver sensorValueReceiver, DelayAction delayAction,
                     Random random, SensorSimulationListener sensorSimulationListener) {

        this.sensorValueReceiver = sensorValueReceiver;
        this.delayAction = delayAction;
        this.configuration = configuration;
        this.sensorSimulationListener = sensorSimulationListener;
        sensorValueGenerator = new SensorValueGenerator(configuration, random);
        delayGenerator = new DelayGenerator(configuration.getDelay(), configuration.getDelayVariation(), random);
    }

    void run(Instant startTime) {
        Instant currentTime = startTime;
        Instant endTime = startTime.plus(configuration.getSimulationDuration());
        int numberOfEventsCreated = 0;
        LOGGER.info("Started simulation run at {}", startTime);
        try {
            while (currentTime.isBefore(endTime)) {
                SensorValue sensorValue = sensorValueGenerator.randomSensorValue(currentTime);
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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public SensorSimulationConfiguration getConfiguration() {
        return configuration;
    }

    @Override
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
