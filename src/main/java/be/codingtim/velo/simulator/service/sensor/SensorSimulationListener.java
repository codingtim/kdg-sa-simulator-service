package be.codingtim.velo.simulator.service.sensor;

/**
 * Callback interface for {@link SensorSimulation} completion.
 */
@FunctionalInterface
interface SensorSimulationListener {

    /**
     * Called when the {@link SensorSimulation} completes.
     * When the simulation ends with an error, this method is also called.
     *
     * @param result result object which can either be successful or failed.
     */
    void simulationCompleted(SensorSimulationResult result);
}
