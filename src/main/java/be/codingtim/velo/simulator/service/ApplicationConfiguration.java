package be.codingtim.velo.simulator.service;

import be.codingtim.velo.simulator.service.sensor.DelayAction;
import be.codingtim.velo.simulator.service.sensor.SensorSimulator;
import be.codingtim.velo.simulator.service.sensor.SensorValueReceiver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
        Log log = LogFactory.getLog("SensorValues");
        return log::trace;
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
