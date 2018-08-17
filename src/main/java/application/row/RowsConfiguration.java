package application.row;

import application.google.CredentialAuthorizer;
import application.google.GoogleSheetsConnector;
import application.google.GoogleSheetsRetriever;
import com.google.api.client.auth.oauth2.Credential;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RowsConfiguration {
  @Bean
  public ResponseToRowsTransformer transformer(CredentialAuthorizer credentialAuthorizer, GoogleSheetsConnector googleSheetsConnector) throws IOException {
    Credential credential = credentialAuthorizer.authorize();
    GoogleSheetsRetriever googleSheetsRetriever = new GoogleSheetsRetriever(googleSheetsConnector.connect(credential));
    return new ResponseToRowsTransformer(googleSheetsRetriever);
  }
}
