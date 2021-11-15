package se.lernholt.integration.email.config;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderSubmitMessageHandler implements GenericHandler<Order> {

    private final RestTemplate  rest;
    private final ApiProperties apiProps;

    @Override
    public Object handle(Order order, MessageHeaders headers) {
        rest.postForObject(apiProps.getUrl(), order, String.class);
        return null;
    }
}
