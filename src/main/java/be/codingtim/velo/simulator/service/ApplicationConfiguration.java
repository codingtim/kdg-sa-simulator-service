package be.codingtim.velo.simulator.service;

import be.codingtim.velo.simulator.service.sensor.SensorSimulator;
import be.codingtim.velo.simulator.service.sensor.delay.DelayAction;
import be.codingtim.velo.simulator.service.sensor.receiver.SensorValueReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;

import java.util.Random;

@Configuration
@Import({

})
@ComponentScan(basePackageClasses = {
        SensorSimulator.class
})
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Bean
    public SensorValueReceiver sensorValueReceiver() {
        //dummy receiver for now, to be replaced with AMQP appending receiver
        Logger log = LoggerFactory.getLogger("SensorValues");
        return sensorValue -> log.trace(sensorValue.toString());
    }

    @Bean
    public DelayAction delayAction() {
        return delay -> {

        };
    }

    @Bean
    public Random random() {
        return new Random();
    }
}
