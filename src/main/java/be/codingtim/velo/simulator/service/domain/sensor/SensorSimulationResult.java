package be.codingtim.velo.simulator.service.domain.sensor;

import java.time.Instant;

public class SensorSimulationResult {

    private final boolean success;
    private final int numberOfEventsGenerated;
    private final String errorMessage;
    private final Instant startTime;
    private final Instant endTime;

    public SensorSimulationResult(boolean success, int numberOfEventsGenerated, String errorMessage, Instant startTime, Instant endTime) {
        this.success = success;
        this.numberOfEventsGenerated = numberOfEventsGenerated;
        this.errorMessage = errorMessage;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getNumberOfEventsGenerated() {
        return numberOfEventsGenerated;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "SensorSimulationResult{" +
                "success=" + success +
                ", numberOfEventsGenerated=" + numberOfEventsGenerated +
                ", errorMessage='" + errorMessage + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
