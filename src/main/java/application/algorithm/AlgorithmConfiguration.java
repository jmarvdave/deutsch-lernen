package application.algorithm;

import application.google.GoogleCredentialAuthorizer;
import application.google.GoogleSheetsConnector;
import application.google.GoogleSheetsRetriever;
import com.google.api.client.auth.oauth2.Credential;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AlgorithmConfiguration {
  @Bean
  ResponseToAlgorithmTransformer responseToAlgorithmTransformer(
      GoogleCredentialAuthorizer googleCredentialAuthorizer, GoogleSheetsConnector googleSheetsConnector)
      throws IOException {
    Credential authorize = googleCredentialAuthorizer.authorize();
    GoogleSheetsRetriever googleSheetsRetriever = new GoogleSheetsRetriever(googleSheetsConnector.connect(authorize));
    return new ResponseToAlgorithmTransformer(googleSheetsRetriever);
  }

  @Bean
  AlgorithmSettings algorithmSettings(ResponseToAlgorithmTransformer responseToAlgorithmTransformer) {
    return new AlgorithmSettings(responseToAlgorithmTransformer);
  }

  @Bean
  AlgorithmExecutor algorithmExecutor(AlgorithmSettings algorithmSettings) {
    return new AlgorithmExecutor(algorithmSettings);
  }
}
