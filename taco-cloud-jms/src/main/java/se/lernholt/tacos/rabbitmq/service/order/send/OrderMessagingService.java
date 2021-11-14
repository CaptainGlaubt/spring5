package se.lernholt.tacos.rabbitmq.service.order.send;

import se.lernholt.tacos.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
