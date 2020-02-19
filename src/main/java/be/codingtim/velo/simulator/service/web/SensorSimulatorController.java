package be.codingtim.velo.simulator.service.web;

import be.codingtim.velo.simulator.service.sensor.SensorSimulationView;
import be.codingtim.velo.simulator.service.sensor.SensorSimulator;
import be.codingtim.velo.simulator.service.web.dto.sensor.SensorSimulationConfigurationDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/sensor-simulations")
public class SensorSimulatorController {

    private final SensorSimulator sensorSimulator;

    public SensorSimulatorController(SensorSimulator sensorSimulator) {
        this.sensorSimulator = sensorSimulator;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addSimulation(@RequestBody SensorSimulationConfigurationDto dto) {
        SensorSimulationView sensorSimulation = sensorSimulator.addSimulation(dto);
        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, "/api/sensor-simulations/" + sensorSimulation.getId())
                .build();
    }
}
