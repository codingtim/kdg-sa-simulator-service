package be.codingtim.velo.simulator.service.sensor;

import java.time.Instant;

public class SensorValue {
    private final Instant instant;
    private final String type;
    private final int value;
    private final double latitude;
    private final double longitude;

    public SensorValue(Instant instant, String type, int value, double latitude, double longitude) {
        this.instant = instant;
        this.type = type;
        this.value = value;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "SensorValue{" +
                "instant=" + instant +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
