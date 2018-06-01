package application.configuration;

import application.transformer.ResponseToSheetTransformer;
import application.utility.SheetsRetriever;
import com.google.api.client.auth.oauth2.Credential;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SheetsConfiguration {
    
    @Bean
    public CredentialAuthorizer credentialAuthorizer() {
        return new CredentialAuthorizer();
    }

    @Bean
    public ResponseToSheetTransformer transformer(CredentialAuthorizer credentialAuthorizer, SheetsConnector sheetsConnector) throws IOException {
        Credential credential = credentialAuthorizer.authorize();
        SheetsRetriever sheetsRetriever = new SheetsRetriever(sheetsConnector.connect(credential));
        return new ResponseToSheetTransformer(sheetsRetriever);
    }
}
