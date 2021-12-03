package se.lernholt.tacos.kafka.service.order.receive;

import javax.jms.JMSException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Order;

@Component
@RequiredArgsConstructor
public class KafkaOrderReceiverAndCoverterService implements OrderReceiverService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public Order receiveOrder() throws MessageConversionException, JMSException {
        return (Order) rabbitTemplate.receiveAndConvert("tacocloud.order.queue",
                new ParameterizedTypeReference<Order>() {
                });
    }
}
