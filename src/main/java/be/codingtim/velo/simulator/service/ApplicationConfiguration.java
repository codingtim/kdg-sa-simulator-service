package be.codingtim.velo.simulator.service;

import be.codingtim.velo.simulator.service.domain.sensor.SensorSimulator;
import be.codingtim.velo.simulator.service.gateway.sensor.SensorServiceGatewayConfiguration;
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
