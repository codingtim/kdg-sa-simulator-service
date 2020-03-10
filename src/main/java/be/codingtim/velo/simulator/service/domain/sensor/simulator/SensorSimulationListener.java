package be.codingtim.velo.simulator.service.domain.sensor.simulator;

/**
 * Callback interface for {@link SensorSimulation} completion.
 */
@FunctionalInterface
interface SensorSimulationListener {

    /**
     * Called when the {@link SensorSimulation} completes.
     * When the simulation ends with an error, this method is also called.
     *
     * @param sensorSimulation the {@link SensorSimulation} that completed.
     */
    void simulationCompleted(SensorSimulation sensorSimulation);
}
