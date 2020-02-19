package be.codingtim.velo.simulator.service.web.dto.sensor;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SensorSimulatorSnapshotDto {

    @JsonProperty
    public final List<SensorSimulationDto> waitingSimulations;
    @JsonProperty
    public final List<SensorSimulationDto> runningSimulations;
    @JsonProperty
    public final List<SensorSimulationDto> completedSimulations;

    public SensorSimulatorSnapshotDto(List<SensorSimulationDto> waitingSimulations,
                                      List<SensorSimulationDto> runningSimulations,
                                      List<SensorSimulationDto> completedSimulations)
    {
        this.waitingSimulations = waitingSimulations;
        this.runningSimulations = runningSimulations;
        this.completedSimulations = completedSimulations;
    }
}
