package be.codingtim.velo.simulator.service.domain.sensor.delay;

import org.springframework.stereotype.Component;

@Component("realtimeDelayAction")
class RealtimeDelayAction implements DelayAction {
    @Override
    public void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException("Delay failed.", e);
        }
    }
}
