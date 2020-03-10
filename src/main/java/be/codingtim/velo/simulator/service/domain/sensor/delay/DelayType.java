package be.codingtim.velo.simulator.service.domain.sensor.delay;

public enum DelayType {
    NO_DELAY {
        @Override
        public <OUT> OUT accept(Visitor<OUT> visitor) {
            return visitor.noDelay();
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
        OUT noDelay();

        OUT realtime();
    }
}
