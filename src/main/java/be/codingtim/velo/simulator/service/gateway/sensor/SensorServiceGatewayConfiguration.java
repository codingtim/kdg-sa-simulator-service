package be.codingtim.velo.simulator.service.gateway.sensor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SensorServiceGatewayConfiguration {

    @Bean
    public SensorServiceGateway sensorServiceGateway(RabbitTemplate rabbitTemplate,
                                                     @Value("${rabbitmq.exchange}") String exchange,
                                                     @Value("${rabbitmq.routingKey}") String routingKey) {
        return new SensorServiceGateway(rabbitTemplate, exchange, routingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(@Value("${rabbitmq.username}") String username,
                                         @Value("${rabbitmq.password}") String password,
                                         @Value("${rabbitmq.virtualHost}") String virtualHost,
                                         @Value("${rabbitmq.host}") String host,
                                         @Value("${rabbitmq.port}") Integer port) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPort(port);

        ObjectMapper objectMapper = new ObjectMapper()
                .findAndRegisterModules()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
        return rabbitTemplate;
    }
}
