package se.lernholt.integration.email.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "integration-flow.email")
@Data
public class EmailIntegrationFlowConfig {

    private String username;
    private String password;
    private String host;
    private String mailbox;
    private long   pollRate = 30000;

    public String getImapUrl() {
        return String.format("imaps://%s:%s@%s/%s", username, password, host, mailbox);
    }
}
