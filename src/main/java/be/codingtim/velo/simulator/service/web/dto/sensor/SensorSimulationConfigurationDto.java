package be.codingtim.velo.simulator.service.web.dto.sensor;

import be.codingtim.velo.simulator.service.sensor.SensorSimulationConfiguration;
import be.codingtim.velo.simulator.service.sensor.delay.DelayType;
import be.codingtim.velo.simulator.service.sensor.generator.location.LocationConfiguration;
import be.codingtim.velo.simulator.service.sensor.generator.sensor.SensorConfiguration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class SensorSimulationConfigurationDto implements SensorSimulationConfiguration {

    @JsonProperty
    private String duration;
    @JsonProperty
    private int delay;
    @JsonProperty
    private int delayVariation;
    @JsonProperty
    private String delayType;
    @JsonProperty
    private LocationConfigurationDto locationConfiguration;
    @JsonProperty
    private List<SensorConfigurationDto> sensorConfigurations;

    SensorSimulationConfigurationDto() {
        //default constructor for jackson
    }

    public SensorSimulationConfigurationDto(String duration, int delay, int delayVariation, String delayType,
                                            LocationConfigurationDto locationConfiguration,
                                            List<SensorConfigurationDto> sensorConfigurations) {
        this.duration = duration;
        this.delay = delay;
        this.delayVariation = delayVariation;
        this.delayType = delayType;
        this.locationConfiguration = locationConfiguration;
        this.sensorConfigurations = sensorConfigurations;
    }

    @Override
    @JsonIgnore
    public Duration getSimulationDuration() {
        return Duration.parse(duration);
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public int getDelayVariation() {
        return delayVariation;
    }

    @Override
    public DelayType getDelayType() {
        return DelayType.valueOf(delayType);
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return locationConfiguration;
    }

    @Override
    public List<? extends SensorConfiguration> getSensorConfigurations() {
        return sensorConfigurations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorSimulationConfigurationDto that = (SensorSimulationConfigurationDto) o;
        return delay == that.delay &&
                delayVariation == that.delayVariation &&
                Objects.equals(delayType, that.delayType) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(locationConfiguration, that.locationConfiguration) &&
                Objects.equals(sensorConfigurations, that.sensorConfigurations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, delay, delayVariation);
    }
}
