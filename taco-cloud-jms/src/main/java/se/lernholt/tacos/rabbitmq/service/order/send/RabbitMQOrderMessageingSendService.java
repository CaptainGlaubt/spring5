package se.lernholt.tacos.rabbitmq.service.order.send;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Order;

@Component
@RequiredArgsConstructor
public class RabbitMQOrderMessageingSendService implements OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrder(Order order) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("X_ORDER_SOURCE", "WEB");
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        Message message = messageConverter.toMessage(messageConverter, messageProperties);
        rabbitTemplate.send("tacocloud.order", message);
    }

}
