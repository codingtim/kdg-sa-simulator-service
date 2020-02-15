package be.codingtim.velo.simulator.service.sensor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class SensorSimulator implements SensorSimulationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorSimulation.class);

    private final int maximumNumberOfConcurrentSimulations;
    private final SensorValueReceiver sensorValueReceiver;
    private final DelayAction delayAction;
    private final Random random;

    private final AtomicInteger numberOfSimulations = new AtomicInteger(0);
    private final Queue<SensorSimulation> waitingSimulations = new LinkedList<>();
    private final List<SensorSimulationResult> completedSensorSimulations = new LinkedList<>();

    public SensorSimulator(@Value("${sensor.simulator.max.concurrent.simulations") int maximumNumberOfConcurrentSimulations,
                           SensorValueReceiver sensorValueReceiver,
                           DelayAction delayAction,
                           Random random) {
        this.maximumNumberOfConcurrentSimulations = maximumNumberOfConcurrentSimulations;
        this.sensorValueReceiver = sensorValueReceiver;
        this.delayAction = delayAction;
        this.random = random;
    }

    public synchronized void addSimulation(SensorSimulationConfiguration configuration) {
        waitingSimulations.add(new SensorSimulation(configuration, sensorValueReceiver, delayAction, random, this));
        LOGGER.info("Added simulation to waiting list");
        startSimulationIfPossible();
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
    public void simulationCompleted(SensorSimulationResult result) {
        synchronized (this) {
            completedSensorSimulations.add(result);
            numberOfSimulations.decrementAndGet();
            startSimulationIfPossible();
        }
    }

    public List<SensorSimulationResult> getCompletedSensorSimulations() {
        return new ArrayList<>(completedSensorSimulations);
    }
}
