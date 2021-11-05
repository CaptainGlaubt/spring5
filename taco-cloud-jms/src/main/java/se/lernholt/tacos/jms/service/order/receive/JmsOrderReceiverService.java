package se.lernholt.tacos.jms.service.order.receive;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Order;

@Service
@RequiredArgsConstructor
public class JmsOrderReceiverService implements OrderReceiverService {

    private final JmsTemplate      jmsTemplate;
    private final MessageConverter messageConverter;

    @Override
    public Order receiveOrder() throws MessageConversionException, JMSException {
        Message message = jmsTemplate.receive("tacocloud.order.queue");
        return (Order) messageConverter.fromMessage(message);
    }
}
