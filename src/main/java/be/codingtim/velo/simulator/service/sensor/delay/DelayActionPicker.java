package be.codingtim.velo.simulator.service.sensor.delay;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
class DelayActionPicker implements DelayActions, DelayType.Visitor<DelayAction> {

    private final DelayAction noDelayAction;

    public DelayActionPicker(@Qualifier("noDelayAction") DelayAction noDelayAction) {
        this.noDelayAction = noDelayAction;
    }

    @Override
    public DelayAction forType(DelayType delayType) {
        return delayType.accept(this);
    }

    @Override
    public DelayAction noDelayAction() {
        return noDelayAction;
    }
}
