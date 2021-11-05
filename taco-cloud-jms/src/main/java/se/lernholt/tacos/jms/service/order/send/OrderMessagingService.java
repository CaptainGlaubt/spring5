package se.lernholt.tacos.jms.service.order.send;

import se.lernholt.tacos.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
