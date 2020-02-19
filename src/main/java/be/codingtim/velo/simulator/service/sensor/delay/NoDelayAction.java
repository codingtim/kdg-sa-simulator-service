package be.codingtim.velo.simulator.service.sensor.delay;

import org.springframework.stereotype.Component;

@Component("noDelayAction")
class NoDelayAction implements DelayAction {
    @Override
    public void delay(int delay) {
        //nothing to do
    }
}
