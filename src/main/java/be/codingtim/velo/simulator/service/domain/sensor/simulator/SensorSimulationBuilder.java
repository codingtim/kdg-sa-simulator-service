package be.codingtim.velo.simulator.service.domain.sensor.simulator;

import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulationConfiguration;
import be.codingtim.velo.simulator.service.domain.sensor.delay.DelayActions;
import be.codingtim.velo.simulator.service.domain.sensor.receiver.SensorValueReceiver;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
class SensorSimulationBuilder {
    private final SensorValueReceiver sensorValueReceiver;
    private final DelayActions delayActions;
    private final Random random;

    SensorSimulationBuilder(SensorValueReceiver sensorValueReceiver, DelayActions delayActions, Random random) {
        this.sensorValueReceiver = sensorValueReceiver;
        this.delayActions = delayActions;
        this.random = random;
    }

    SensorSimulation simulationFor(SensorSimulationConfiguration configuration, SensorSimulationListener listener) {
        return new SensorSimulation(
                configuration,
                sensorValueReceiver,
                delayActions.forType(configuration.getDelayType()),
                random,
                listener
        );
    }
}
