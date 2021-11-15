package se.lernholt.integration.email.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;

@Configuration
public class EmailIntegrationFlow {

    @Bean
    public IntegrationFlow tacoOrderEmailFlow(EmailIntegrationFlowConfig emailIntegrationFlowConfig,
            EmailToOrderTransformer emailToOrderTransformer, OrderSubmitMessageHandler orderSubmitHandler) {
        return IntegrationFlows
                .from(Mail.imapInboundAdapter(emailIntegrationFlowConfig.getImapUrl()),
                        e -> e.poller(Pollers.fixedDelay(emailIntegrationFlowConfig.getPollRate())))
                .transform(emailToOrderTransformer)
                .handle(orderSubmitHandler)
                .get();
    }

}
