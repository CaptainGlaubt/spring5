package se.lernholt.tacos.jms.service.order.send;

import javax.jms.Message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Order;

@Service
@RequiredArgsConstructor
public class JmsOrderMessagingSendService implements OrderMessagingService {

    private final JmsTemplate jmsTemplate;

    @Override
    public void sendOrder(Order order) {
        jmsTemplate.send("tacocloud.order.queue", session -> {
            Message message = session.createObjectMessage(order);
            message.setStringProperty("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }
}
