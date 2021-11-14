package se.lernholt.tacos.jms.service.order.receive;

import javax.jms.JMSException;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Order;

@Component
@RequiredArgsConstructor
public class JmsOrderReceiverAndCoverterService implements OrderReceiverService {

    private final JmsTemplate jmsTemplate;

    @Override
    public Order receiveOrder() throws MessageConversionException, JMSException {
        return (Order) jmsTemplate.receiveAndConvert("tacocloud.order.queue");
    }
}
