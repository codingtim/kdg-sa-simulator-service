package be.codingtim.velo.simulator.service.sensor.delay;

/**
 * Functional interfaces that defines the behaviour that should be executed during a delay.
 * This interface allows different implementations during test or runtime.
 * For real world simulation we could use real delays. During (load) testing we could implement this with no delay.
 */
@FunctionalInterface
public interface DelayAction {
    void delay(int delay);
}
