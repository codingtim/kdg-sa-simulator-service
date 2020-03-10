package be.codingtim.velo.simulator.service.domain.sensor.generator.sensor;

public class SensorReading {
    private final String type;
    private final int value;

    public SensorReading(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
