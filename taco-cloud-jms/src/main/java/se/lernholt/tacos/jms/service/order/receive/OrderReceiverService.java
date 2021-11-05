package se.lernholt.tacos.jms.service.order.receive;

import javax.jms.JMSException;

import org.springframework.jms.support.converter.MessageConversionException;

import se.lernholt.tacos.Order;

public interface OrderReceiverService {
    public Order receiveOrder() throws MessageConversionException, JMSException;
}
