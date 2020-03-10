package be.codingtim.velo.simulator.service.domain.sensor.generator.location;

import java.util.Random;

public class LocationGenerator {

    private final CoordinateGenerator latitudeGenerator;
    private final CoordinateGenerator longitudeGenerator;

    public LocationGenerator(LocationConfiguration configuration, Random random) {
        this.latitudeGenerator = new CoordinateGenerator(configuration.getLatitudeConfiguration(), random);
        this.longitudeGenerator = new CoordinateGenerator(configuration.getLongitudeConfiguration(), random);
    }

    public Location randomLocation() {
        return new Location(
                latitudeGenerator.randomCoordinate(),
                longitudeGenerator.randomCoordinate()
        );
    }

}
