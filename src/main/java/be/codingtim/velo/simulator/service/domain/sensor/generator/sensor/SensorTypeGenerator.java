package be.codingtim.velo.simulator.service.domain.sensor.generator.sensor;

import java.util.Collection;
import java.util.Random;

class SensorTypeGenerator {

    private final String[] types;
    private final int numberOfPossibleTypes;
    private final Random random;

    public SensorTypeGenerator(Collection<String> types, Random random) {
        this.types = types.toArray(new String[0]);
        this.numberOfPossibleTypes = this.types.length;
        this.random = random;
    }

    public String randomType() {
        return types[random.nextInt(numberOfPossibleTypes)];
    }
}
