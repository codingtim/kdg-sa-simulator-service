package be.codingtim.velo.simulator.service.sensor;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
class SensorSimulationBuilder {
    private final SensorValueReceiver sensorValueReceiver;
    private final DelayAction delayAction;
    private final Random random;

    SensorSimulationBuilder(SensorValueReceiver sensorValueReceiver, DelayAction delayAction, Random random) {
        this.sensorValueReceiver = sensorValueReceiver;
        this.delayAction = delayAction;
        this.random = random;
    }

    SensorSimulation simulationFor(SensorSimulationConfiguration configuration, SensorSimulationListener listener) {
        return new SensorSimulation(configuration, sensorValueReceiver, delayAction, random, listener);
    }
}
