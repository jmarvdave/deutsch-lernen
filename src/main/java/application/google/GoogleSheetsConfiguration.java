package application.google;

import com.google.api.client.auth.oauth2.Credential;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleSheetsConfiguration {
    @Bean
    public GoogleCredentialAuthorizer credentialAuthorizer() {
        return GoogleCredentialAuthorizer.createWithDefaultCredentiaLocation();
    }

    @Bean
    public GoogleSheetsRetriever googleSheetsRetriever(
        GoogleCredentialAuthorizer googleCredentialAuthorizer, GoogleSheetsConnector googleSheetsConnector)
        throws IOException {
        Credential credential = googleCredentialAuthorizer.authorize();
        return new GoogleSheetsRetriever(googleSheetsConnector.connect(credential));
    }
}
