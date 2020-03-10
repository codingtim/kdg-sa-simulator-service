package be.codingtim.velo.simulator.service.web.dto.sensor;

import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulationConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorSimulationDto {

    @JsonProperty
    private final String id;
    @JsonProperty
    private final SensorSimulationConfiguration configuration;
    @JsonProperty
    private final SensorSimulationResultDto result;

    public SensorSimulationDto(String id, SensorSimulationConfiguration configuration, SensorSimulationResultDto result) {
        this.id = id;
        this.configuration = configuration;
        this.result = result;
    }
}
