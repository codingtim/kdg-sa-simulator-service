package be.codingtim.velo.simulator.service.web.dto.sensor;

import be.codingtim.velo.simulator.service.sensor.generator.sensor.SensorConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SensorConfigurationDto implements SensorConfiguration {
    @JsonProperty
    private String type;
    @JsonProperty
    private int lowerBound;
    @JsonProperty
    private int upperBoundExclusive;

    SensorConfigurationDto() {
        //default constructor for jackson
    }

    public SensorConfigurationDto(String type, int lowerBound, int upperBoundExclusive) {
        this.type = type;
        this.lowerBound = lowerBound;
        this.upperBoundExclusive = upperBoundExclusive;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getLowerBound() {
        return lowerBound;
    }

    @Override
    public int getUpperBoundExclusive() {
        return upperBoundExclusive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorConfigurationDto that = (SensorConfigurationDto) o;
        return lowerBound == that.lowerBound &&
                upperBoundExclusive == that.upperBoundExclusive &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
