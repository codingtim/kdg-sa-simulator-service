package be.codingtim.velo.simulator.service.sensor.generator.sensor;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SensorTypeGeneratorTest {

    private final Random random = new Random();
    private final List<String> types = List.of("CO2", "NOx", "O2");
    private final SensorTypeGenerator sensorTypeGenerator = new SensorTypeGenerator(types, random);

    @RepeatedTest(20)
    void generateRandomValue() {
        String type = sensorTypeGenerator.randomType();
        assertNotNull(type);
        assertTrue(types.contains(type));
    }
}