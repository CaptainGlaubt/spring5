package se.lernholt.tacos.kafka.service.order.send;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Order;

@Component
@RequiredArgsConstructor
public class KafkaOrderMessagingSendService implements OrderMessagingService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void sendOrder(Order order) {
        kafkaTemplate.send(null).send("tacocloud.orders.topic", order);
        kafkaTemplate.sendDefault(order);
    }
}
