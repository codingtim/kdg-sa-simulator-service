package be.codingtim.velo.simulator.service.web.dto.sensor;

import be.codingtim.velo.simulator.service.domain.sensor.generator.location.CoordinateConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CoordinateConfigurationDto implements CoordinateConfiguration {

    @JsonProperty
    private String lowerBound;
    @JsonProperty
    private String upperBoundExclusive;

    public CoordinateConfigurationDto() {
        //default constructor for jackson
    }

    public CoordinateConfigurationDto(String lowerBound, String upperBoundExclusive) {
        this.lowerBound = lowerBound;
        this.upperBoundExclusive = upperBoundExclusive;
    }

    @Override
    public double getLowerBound() {
        return Double.parseDouble(lowerBound);
    }

    @Override
    public double getUpperBoundExclusive() {
        return Double.parseDouble(upperBoundExclusive);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateConfigurationDto that = (CoordinateConfigurationDto) o;
        return Objects.equals(lowerBound, that.lowerBound) &&
                Objects.equals(upperBoundExclusive, that.upperBoundExclusive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowerBound, upperBoundExclusive);
    }
}
