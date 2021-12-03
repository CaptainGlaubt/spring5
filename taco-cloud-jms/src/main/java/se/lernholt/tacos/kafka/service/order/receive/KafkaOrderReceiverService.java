package se.lernholt.tacos.kafka.service.order.receive;

import java.util.Objects;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Order;

@Service
@RequiredArgsConstructor
public class KafkaOrderReceiverService implements OrderReceiverService {

    private final RabbitTemplate   rabbitTemplate;
    private final MessageConverter messageConverter;

    @Override
    public Order receiveOrder() {
        Message message = rabbitTemplate.receive("tacocloud.orders");
        if (Objects.nonNull(message)) {
            return (Order) messageConverter.fromMessage(message);
        }
        return null;
    }
}
