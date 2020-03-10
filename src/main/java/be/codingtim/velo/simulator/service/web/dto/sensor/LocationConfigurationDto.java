package be.codingtim.velo.simulator.service.web.dto.sensor;

import be.codingtim.velo.simulator.service.domain.sensor.generator.location.CoordinateConfiguration;
import be.codingtim.velo.simulator.service.domain.sensor.generator.location.LocationConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class LocationConfigurationDto implements LocationConfiguration {
    @JsonProperty
    private CoordinateConfigurationDto latitudeConfiguration;
    @JsonProperty
    private CoordinateConfigurationDto longitudeConfiguration;

    LocationConfigurationDto() {
        //default constructor for jackson
    }

    public LocationConfigurationDto(CoordinateConfigurationDto latitudeConfiguration, CoordinateConfigurationDto longitudeConfiguration) {
        this.latitudeConfiguration = latitudeConfiguration;
        this.longitudeConfiguration = longitudeConfiguration;
    }

    @Override
    public CoordinateConfiguration getLatitudeConfiguration() {
        return latitudeConfiguration;
    }

    @Override
    public CoordinateConfiguration getLongitudeConfiguration() {
        return longitudeConfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationConfigurationDto that = (LocationConfigurationDto) o;
        return Objects.equals(latitudeConfiguration, that.latitudeConfiguration) &&
                Objects.equals(longitudeConfiguration, that.longitudeConfiguration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitudeConfiguration, longitudeConfiguration);
    }
}
