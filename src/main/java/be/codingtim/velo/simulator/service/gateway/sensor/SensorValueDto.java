package be.codingtim.velo.simulator.service.gateway.sensor;

import be.codingtim.velo.simulator.service.domain.sensor.SensorValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

class SensorValueDto {
    @JsonProperty
    private final Instant timestamp;
    @JsonProperty
    private final String type;
    @JsonProperty
    private final int value;
    @JsonProperty
    private final double latitude;
    @JsonProperty
    private final double longitude;

    public SensorValueDto(SensorValue sensorValue) {
        this.timestamp = sensorValue.getInstant();
        this.type = sensorValue.getType();
        this.value = sensorValue.getValue();
        this.latitude = sensorValue.getLatitude();
        this.longitude = sensorValue.getLongitude();
    }

}
