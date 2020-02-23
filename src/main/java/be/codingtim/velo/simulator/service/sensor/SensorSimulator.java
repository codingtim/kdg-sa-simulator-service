package be.codingtim.velo.simulator.service.sensor;

public interface SensorSimulator {

    /**
     * Creates a new Sensor Simulation with the given configuration.
     * If possible, the new simulation is immediately started asynchronously.
     * If the maximum number of concurrent simulations is reached, the simulation will be started later.
     *
     * @param configuration the configuration for the simulation to add.
     * @return A read only view of the Simulation.
     */
    SensorSimulationView addSimulation(SensorSimulationConfiguration configuration);

    /**
     * Returns a snapshot of the current state of the Simulator.
     *
     * @return the snapshot.
     */
    SensorSimulatorSnapshot getSnapshot();
}
