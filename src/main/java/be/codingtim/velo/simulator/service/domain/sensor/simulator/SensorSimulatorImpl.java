package be.codingtim.velo.simulator.service.domain.sensor.simulator;

import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulationConfiguration;
import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulator;
import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulatorSnapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link SensorSimulatorImpl} runs and tracks the {@link SensorSimulation}s.
 * This class ensures a limited amount of simulations run at the same time.
 */
@Service
class SensorSimulatorImpl implements SensorSimulator, SensorSimulationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorSimulation.class);

    private final int maximumNumberOfConcurrentSimulations;
    private final SensorSimulationBuilder sensorSimulationBuilder;

    private final AtomicInteger numberOfRunningSimulations = new AtomicInteger(0);
    private final Queue<SensorSimulation> waitingSimulations = new LinkedList<>();
    private final List<SensorSimulation> runningSimulations = new ArrayList<>();
    private final List<SensorSimulation> completedSimulations = new ArrayList<>();

    SensorSimulatorImpl(@Value("${sensor.simulator.max.concurrent.simulations}") int maximumNumberOfConcurrentSimulations,
                        SensorSimulationBuilder sensorSimulationBuilder) {
        this.maximumNumberOfConcurrentSimulations = maximumNumberOfConcurrentSimulations;
        this.sensorSimulationBuilder = sensorSimulationBuilder;
    }

    /**
     * Creates a new {@link SensorSimulation} with the given configuration.
     * If possible, the new simulation is immediately started asynchronously.
     * If the maximum number of concurrent simulations is reached, the simulation will be started later.
     *
     * @param configuration the configuration for the simulation to add.
     * @return the created {@link SensorSimulation}.
     */
    @Override
    public SensorSimulation addSimulation(SensorSimulationConfiguration configuration) {
        synchronized (this) {
            SensorSimulation sensorSimulation = sensorSimulationBuilder.simulationFor(configuration, this);
            waitingSimulations.add(sensorSimulation);
            LOGGER.info("Added simulation to waiting list");
            startSimulationIfPossible();
            return sensorSimulation;
        }
    }

    private synchronized void startSimulationIfPossible() {
        LOGGER.info("Checking to start simulation");
        if (waitingSimulations.isEmpty()) {
            LOGGER.info("No simulations waiting to start");
            return;
        }
        if (numberOfRunningSimulations.get() < maximumNumberOfConcurrentSimulations) {
            SensorSimulation sensorSimulation = waitingSimulations.poll();
            numberOfRunningSimulations.addAndGet(1);
            LOGGER.info("Starting simulation");
            new Thread(() -> sensorSimulation.run(Instant.now())).start();
            runningSimulations.add(sensorSimulation);
        } else {
            LOGGER.info("Can not start simulation, maximum number of concurrent simulations is reached.");
        }
    }

    @Override
    public void simulationCompleted(SensorSimulation sensorSimulation) {
        synchronized (this) {
            runningSimulations.remove(sensorSimulation);
            completedSimulations.add(sensorSimulation);
            numberOfRunningSimulations.decrementAndGet();
            startSimulationIfPossible();
        }
    }

    @Override
    public SensorSimulatorSnapshot getSnapshot() {
        return new SensorSimulatorSnapshot(
                new ArrayList<>(waitingSimulations),
                new ArrayList<>(runningSimulations),
                new ArrayList<>(completedSimulations)
        );
    }
}
