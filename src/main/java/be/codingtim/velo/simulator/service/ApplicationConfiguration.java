package be.codingtim.velo.simulator.service;

import be.codingtim.velo.simulator.service.gateway.sensor.SensorServiceGatewayConfiguration;
import be.codingtim.velo.simulator.service.sensor.SensorSimulator;
import org.springframework.context.annotation.*;

import java.util.Random;

@Configuration
@Import({
        SensorServiceGatewayConfiguration.class
})
@ComponentScan(basePackageClasses = {
        SensorSimulator.class
})
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Bean
    public Random random() {
        return new Random();
    }
}
