package be.codingtim.velo.simulator.service.sensor.receiver;

import be.codingtim.velo.simulator.service.sensor.SensorValue;

/**
 * Functional interface that receives the generated {@link SensorValue}.
 * This interface allows different implementations. For example: log or store during testing, or send message to another system.
 */
@FunctionalInterface
public interface SensorValueReceiver {
    void receive(SensorValue sensorValue);
}
