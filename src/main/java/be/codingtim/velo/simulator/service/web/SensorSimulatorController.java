package be.codingtim.velo.simulator.service.web;

import be.codingtim.velo.simulator.service.sensor.SensorSimulator;
import be.codingtim.velo.simulator.service.web.dto.sensor.SensorSimulationConfigurationDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/sensor")
public class SensorSimulatorController {

    private SensorSimulator sensorSimulator;

    public SensorSimulatorController(SensorSimulator sensorSimulator) {
        this.sensorSimulator = sensorSimulator;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addSimulation(@RequestBody SensorSimulationConfigurationDto dto) {
        sensorSimulator.addSimulation(dto);
    }
}
