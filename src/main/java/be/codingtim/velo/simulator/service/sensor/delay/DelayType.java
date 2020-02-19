package be.codingtim.velo.simulator.service.sensor.delay;

public enum DelayType {
    NO_DELAY {
        @Override
        public <OUT> OUT accept(Visitor<OUT> visitor) {
            return visitor.noDelayAction();
        }
    }
    ;

    public abstract <OUT> OUT accept(Visitor<OUT> visitor);

    interface Visitor<OUT> {
        OUT noDelayAction();
    }
}
