package be.codingtim.velo.simulator.service.sensor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link SensorSimulatorImpl} runs and tracks the {@link SensorSimulation}s.
 * This class ensures a limited amount of simulations run at the same time.
 */
@Service
class SensorSimulatorImpl implements SensorSimulator, SensorSimulationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorSimulation.class);

    private final int maximumNumberOfConcurrentSimulations;
    private final SensorValueReceiver sensorValueReceiver;
    private final DelayAction delayAction;
    private final Random random;

    private final AtomicInteger numberOfSimulations = new AtomicInteger(0);
    private final Queue<SensorSimulation> waitingSimulations = new LinkedList<>();
    private final List<SensorSimulation> completedSensorSimulations = new ArrayList<>();

    SensorSimulatorImpl(@Value("${sensor.simulator.max.concurrent.simulations}") int maximumNumberOfConcurrentSimulations,
                        SensorValueReceiver sensorValueReceiver,
                        DelayAction delayAction,
                        Random random) {
        this.maximumNumberOfConcurrentSimulations = maximumNumberOfConcurrentSimulations;
        this.sensorValueReceiver = sensorValueReceiver;
        this.delayAction = delayAction;
        this.random = random;
    }

    /**
     * Creates a new {@link SensorSimulation} with the given configuration.
     * If possible, the new simulation is immediately started asynchronously.
     * If the maximum number of concurrent simulations is reached, the simulation will be started later.
     *
     * @param configuration the configuration for the simulation to add.
     */
    @Override
    public void addSimulation(SensorSimulationConfiguration configuration) {
        synchronized(this) {
            waitingSimulations.add(new SensorSimulation(configuration, sensorValueReceiver, delayAction, random, this));
            LOGGER.info("Added simulation to waiting list");
            startSimulationIfPossible();
        }
    }

    private synchronized void startSimulationIfPossible() {
        LOGGER.info("Checking to start simulation");
        if (waitingSimulations.isEmpty()) {
            LOGGER.info("No simulations waiting to start");
            return;
        }
        if (numberOfSimulations.get() < maximumNumberOfConcurrentSimulations) {
            SensorSimulation sensorSimulation = waitingSimulations.poll();
            numberOfSimulations.addAndGet(1);
            LOGGER.info("Starting simulation");
            new Thread(() -> sensorSimulation.run(Instant.now())).start();
        } else {
            LOGGER.info("Can not start simulation, maximum number of concurrent simulations is reached.");
        }
    }

    @Override
    public void simulationCompleted(SensorSimulation sensorSimulation) {
        synchronized (this) {
            completedSensorSimulations.add(sensorSimulation);
            numberOfSimulations.decrementAndGet();
            startSimulationIfPossible();
        }
    }

    public List<SensorSimulation> getCompletedSensorSimulations() {
        return new ArrayList<>(completedSensorSimulations);
    }
}
