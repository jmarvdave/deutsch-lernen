package application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CredentialsConfiguration {
    @Bean
    public CredentialAuthorizer credentialAuthorizer() {
        return new CredentialAuthorizer();
    }
}
