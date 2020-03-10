package be.codingtim.velo.simulator.service.domain.sensor.generator.sensor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SensorReadingGenerator {

    private final Map<String, SensorValueGenerator> valueGeneratorForType;
    private final SensorTypeGenerator sensorTypeGenerator;

    public SensorReadingGenerator(List<? extends SensorConfiguration> sensorConfigurations, Random random) {
        valueGeneratorForType = new HashMap<>();
        for (SensorConfiguration sensorConfiguration : sensorConfigurations) {
            valueGeneratorForType.put(
                    sensorConfiguration.getType(),
                    new SensorValueGenerator(sensorConfiguration.getLowerBound(), sensorConfiguration.getUpperBoundExclusive(), random)
            );
        }
        this.sensorTypeGenerator = new SensorTypeGenerator(valueGeneratorForType.keySet(), random);
    }

    public SensorReading randomSensorReading() {
        String type = sensorTypeGenerator.randomType();
        return new SensorReading(
                type,
                valueGeneratorForType.get(type).randomValue()
        );
    }
}
