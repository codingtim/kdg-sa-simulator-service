package be.codingtim.velo.simulator.service.domain.sensor.generator.location;

public class DummyLocationConfiguration implements LocationConfiguration {

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
