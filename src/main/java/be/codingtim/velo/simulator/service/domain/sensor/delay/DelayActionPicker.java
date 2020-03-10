package be.codingtim.velo.simulator.service.domain.sensor.delay;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
class DelayActionPicker implements DelayActions, DelayType.Visitor<DelayAction> {

    private final DelayAction noDelayAction;
    private final DelayAction realtimeDelayAction;

    public DelayActionPicker(
            @Qualifier("noDelayAction") DelayAction noDelayAction,
            @Qualifier("realtimeDelayAction") DelayAction realtimeDelayAction
    ) {
        this.noDelayAction = noDelayAction;
        this.realtimeDelayAction = realtimeDelayAction;
    }

    @Override
    public DelayAction forType(DelayType delayType) {
        return delayType.accept(this);
    }

    @Override
    public DelayAction noDelay() {
        return noDelayAction;
    }

    @Override
    public DelayAction realtime() {
        return realtimeDelayAction;
    }
}
