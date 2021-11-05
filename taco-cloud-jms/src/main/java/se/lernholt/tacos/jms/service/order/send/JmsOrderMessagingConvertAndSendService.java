package se.lernholt.tacos.jms.service.order.send;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Order;

@Service
@RequiredArgsConstructor
public class JmsOrderMessagingConvertAndSendService implements OrderMessagingService {

    private final JmsTemplate jmsTemplate;

    @Override
    public void sendOrder(Order order) {
        jmsTemplate.convertAndSend("tacocloud.order.queue", order,
                JmsOrderMessagingConvertAndSendService::addOrderSource);
    }

    public static Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
