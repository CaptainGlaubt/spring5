package se.lernholt.tacos.jms.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import se.lernholt.tacos.Order;

@Component
@Slf4j
public class OrderListener {

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        log.info("Received order: {}.", order);
    }
}
