package be.codingtim.velo.simulator.service.gateway.sensor;

import be.codingtim.velo.simulator.service.sensor.SensorValue;
import be.codingtim.velo.simulator.service.sensor.receiver.SensorValueReceiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
class SensorServiceGateway implements SensorValueReceiver {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public SensorServiceGateway(RabbitTemplate rabbitTemplate, String exchange, String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public void receive(SensorValue sensorValue) {
        rabbitTemplate.convertAndSend(exchange, routingKey, new SensorValueDto(sensorValue));
    }
}
