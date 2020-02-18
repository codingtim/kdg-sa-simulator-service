package be.codingtim.velo.simulator.service.web;

import be.codingtim.velo.simulator.service.sensor.SensorSimulationConfiguration;
import be.codingtim.velo.simulator.service.sensor.SensorSimulationResult;
import be.codingtim.velo.simulator.service.sensor.SensorSimulationView;
import be.codingtim.velo.simulator.service.sensor.SensorSimulator;
import be.codingtim.velo.simulator.service.web.dto.sensor.CoordinateConfigurationDto;
import be.codingtim.velo.simulator.service.web.dto.sensor.LocationConfigurationDto;
import be.codingtim.velo.simulator.service.web.dto.sensor.SensorConfigurationDto;
import be.codingtim.velo.simulator.service.web.dto.sensor.SensorSimulationConfigurationDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SensorSimulatorControllerTest {

    private final DummySensorSimulator sensorSimulator = new DummySensorSimulator();
    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(new SensorSimulatorController(sensorSimulator))
            .build();

    @Test
    void addSimulation() throws Exception {
        mockMvc.perform(post("/api/sensor")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"duration\": \"PT15M\",\n" +
                        "  \"delay\": 2000,\n" +
                        "  \"delayVariation\": 150,\n" +
                        "  \"locationConfiguration\": {\n" +
                        "    \"latitudeConfiguration\": {\n" +
                        "      \"lowerBound\": 51.2012806,\n" +
                        "      \"upperBoundExclusive\": 51.2064643\n" +
                        "    },\n" +
                        "    \"longitudeConfiguration\": {\n" +
                        "      \"lowerBound\": 4.5827843,\n" +
                        "      \"upperBoundExclusive\": 4.6048775\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"sensorConfigurations\": [\n" +
                        "    {\n" +
                        "      \"type\": \"CO2\",\n" +
                        "      \"lowerBound\": 0,\n" +
                        "      \"upperBoundExclusive\": 150\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"type\": \"NOx\",\n" +
                        "      \"lowerBound\": 0,\n" +
                        "      \"upperBoundExclusive\": 75\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"))
                .andExpect(status().isAccepted())
                .andExpect(header().string(LOCATION, (String) "/api/sensor/12345"))
        ;
        SensorSimulationConfigurationDto expected = new SensorSimulationConfigurationDto("PT15M", 2000, 150,
                new LocationConfigurationDto(
                        new CoordinateConfigurationDto("51.2012806", "51.2064643"),
                        new CoordinateConfigurationDto("4.5827843", "4.6048775")
                ),
                List.of(
                        new SensorConfigurationDto("CO2", 0, 150),
                        new SensorConfigurationDto("NOx", 0, 75)
                )
        );
        assertEquals(expected, sensorSimulator.configuration);
    }

    private static class DummySensorSimulator implements SensorSimulator {

        private SensorSimulationConfiguration configuration;

        @Override
        public SensorSimulationView addSimulation(SensorSimulationConfiguration configuration) {
            this.configuration = configuration;
            return new DummySensorSimulationView();
        }
    }

    private static class DummySensorSimulationView implements SensorSimulationView {

        @Override
        public String getId() {
            return "12345";
        }

        @Override
        public Optional<SensorSimulationResult> getResult() {
            return Optional.empty();
        }
    }
}