package se.lernholt.tacos.rabbitmq.service.order.send;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Order;

@Component
@RequiredArgsConstructor
public class RabbitMQOrderMessageingConvertAndSendService implements OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend("tacocloud.order", order,
                RabbitMQOrderMessageingConvertAndSendService::postProcessMessage);
    }

    public static Message postProcessMessage(Message message) throws AmqpException {
        MessageProperties props = message.getMessageProperties();
        props.setHeader("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
