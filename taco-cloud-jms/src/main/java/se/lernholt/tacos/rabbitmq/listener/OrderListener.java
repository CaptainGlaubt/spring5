package se.lernholt.tacos.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import se.lernholt.tacos.Order;

@Component
@Slf4j
public class OrderListener {

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        log.info("Received order: {}.", order);
    }
}
