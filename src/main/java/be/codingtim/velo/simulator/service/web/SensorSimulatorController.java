package be.codingtim.velo.simulator.service.web;

import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulationView;
import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulator;
import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulatorSnapshot;
import be.codingtim.velo.simulator.service.web.dto.sensor.SensorSimulationConfigurationDto;
import be.codingtim.velo.simulator.service.web.dto.sensor.SensorSimulationDto;
import be.codingtim.velo.simulator.service.web.dto.sensor.SensorSimulationResultDto;
import be.codingtim.velo.simulator.service.web.dto.sensor.SensorSimulatorSnapshotDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/sensor-simulations")
public class SensorSimulatorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorSimulatorController.class);

    private final SensorSimulator sensorSimulator;
    private final ObjectMapper objectMapper;

    public SensorSimulatorController(SensorSimulator sensorSimulator, ObjectMapper objectMapper) {
        this.sensorSimulator = sensorSimulator;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addSimulation(@RequestBody SensorSimulationConfigurationDto dto) {
        SensorSimulationView sensorSimulation = sensorSimulator.addSimulation(dto);
        return ResponseEntity.accepted()
                .header(HttpHeaders.LOCATION, "/api/sensor-simulations/" + sensorSimulation.getId())
                .build();
    }

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SensorSimulatorSnapshotDto> getSnapshot() {
        SensorSimulatorSnapshot snapshot = sensorSimulator.getSnapshot();
        return ResponseEntity.ok(convert(snapshot));
    }

    @RequestMapping(path = "/default", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SensorSimulationConfigurationDto> getDefault() {
        try (InputStream inputStream = new ClassPathResource("default-sensor-configuration.json").getInputStream()) {
            SensorSimulationConfigurationDto defaultConfiguration = objectMapper.readValue(inputStream, SensorSimulationConfigurationDto.class);
            return ResponseEntity.ok(defaultConfiguration);
        } catch (IOException e) {
            LOGGER.error("Could not retrieve default configuration from file.", e);
            return ResponseEntity.notFound().build();
        }
    }

    private SensorSimulatorSnapshotDto convert(SensorSimulatorSnapshot snapshot) {
        return new SensorSimulatorSnapshotDto(
                convert(snapshot.waitingSimulations),
                convert(snapshot.runningSimulations),
                convert(snapshot.completedSimulations)
        );
    }

    private List<SensorSimulationDto> convert(List<SensorSimulationView> simulationViews) {
        return simulationViews.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private SensorSimulationDto convert(SensorSimulationView simulationView) {
        return new SensorSimulationDto(
                simulationView.getId(),
                simulationView.getConfiguration(),
                simulationView.getResult().map(SensorSimulationResultDto::new).orElse(null)
        );
    }
}
