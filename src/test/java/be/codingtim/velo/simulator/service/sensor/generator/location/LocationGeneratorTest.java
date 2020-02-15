package be.codingtim.velo.simulator.service.sensor.generator.location;

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

    private static class DummyCoordinateConfiguration implements CoordinateConfiguration {

        private final double lowerBound;
        private final double upperBoundExclusive;

        public DummyCoordinateConfiguration(double lowerBound, double upperBoundExclusive) {
            this.lowerBound = lowerBound;
            this.upperBoundExclusive = upperBoundExclusive;
        }

        @Override
        public double getLowerBound() {
            return lowerBound;
        }

        @Override
        public double getUpperBoundExclusive() {
            return upperBoundExclusive;
        }
    }

    private static class DummyLocationConfiguration implements LocationConfiguration {

        private final CoordinateConfiguration latitudeConfiguration;
        private final CoordinateConfiguration longitudeConfiguration;

        public DummyLocationConfiguration(CoordinateConfiguration latitudeConfiguration, CoordinateConfiguration longitudeConfiguration) {
            this.latitudeConfiguration = latitudeConfiguration;
            this.longitudeConfiguration = longitudeConfiguration;
        }

        @Override
        public CoordinateConfiguration getLatitudeConfiguration() {
            return latitudeConfiguration;
        }

        @Override
        public CoordinateConfiguration getLongitudeConfiguration() {
            return longitudeConfiguration;
        }
    }
}