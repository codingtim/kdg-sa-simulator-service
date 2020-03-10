package be.codingtim.velo.simulator.service.web.dto.sensor;

import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulationResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class SensorSimulationResultDto {

    @JsonProperty
    private final boolean success;
    @JsonProperty
    private final int numberOfEventsGenerated;
    @JsonProperty
    private final String errorMessage;
    @JsonProperty
    private final Instant startTime;
    @JsonProperty
    private final Instant endTime;

    public SensorSimulationResultDto(boolean success, int numberOfEventsGenerated, String errorMessage, Instant startTime, Instant endTime) {
        this.success = success;
        this.numberOfEventsGenerated = numberOfEventsGenerated;
        this.errorMessage = errorMessage;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SensorSimulationResultDto(SensorSimulationResult result) {
        this(result.isSuccess(), result.getNumberOfEventsGenerated(), result.getErrorMessage(), result.getStartTime(), result.getEndTime());
    }
}
