package be.codingtim.velo.simulator.service.sensor.delay;

public enum DelayType {
    NO_DELAY {
        @Override
        public <OUT> OUT accept(Visitor<OUT> visitor) {
            return visitor.noDelayAction();
        }
    },
    REALTIME {
        @Override
        public <OUT> OUT accept(Visitor<OUT> visitor) {
            return visitor.realtime();
        }
    }
    ;

    public abstract <OUT> OUT accept(Visitor<OUT> visitor);

    interface Visitor<OUT> {
        OUT noDelayAction();

        OUT realtime();
    }
}
