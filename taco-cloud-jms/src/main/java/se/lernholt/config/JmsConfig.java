package se.lernholt.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import se.lernholt.tacos.Order;

@Configuration
public class JmsConfig {

    /**
     * Overrides the default {@link SimpleMessageConverter}.
     */
    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("order", Order.class);
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }

}
