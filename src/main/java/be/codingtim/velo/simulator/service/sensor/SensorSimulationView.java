package be.codingtim.velo.simulator.service.sensor;

import java.util.Optional;

public interface SensorSimulationView {

    String getId();

    SensorSimulationConfiguration getConfiguration();

    Optional<SensorSimulationResult> getResult();

}
