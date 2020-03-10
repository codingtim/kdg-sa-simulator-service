package be.codingtim.velo.simulator.service.domain.sensor.generator.location;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LocationGeneratorTest {

    private final LocationConfiguration locationConfiguration = new DummyLocationConfiguration(
            new DummyCoordinateConfiguration(1, 2),
            new DummyCoordinateConfiguration(-2, -1)
    );
    private final LocationGenerator locationGenerator = new LocationGenerator(locationConfiguration, new Random());

    @RepeatedTest(20)
    void randomLocation() {
        Location location = locationGenerator.randomLocation();
        assertTrue(location.getLatitude() >= 1);
        assertTrue(location.getLatitude() < 2);
        assertTrue(location.getLongitude() >= -2);
        assertTrue(location.getLongitude() < -1);
    }

}