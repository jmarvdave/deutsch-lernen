package application.google;

import com.google.api.client.auth.oauth2.Credential;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleSheetsConfiguration {
    @Bean
    public CredentialAuthorizer credentialAuthorizer() {
        return CredentialAuthorizer.createWithDefaultCredentiaLocation();
    }

    @Bean
    public GoogleSheetsRetriever googleSheetsRetriever(CredentialAuthorizer credentialAuthorizer, GoogleSheetsConnector googleSheetsConnector)
        throws IOException {
        Credential credential = credentialAuthorizer.authorize();
        return new GoogleSheetsRetriever(googleSheetsConnector.connect(credential));
    }
}
