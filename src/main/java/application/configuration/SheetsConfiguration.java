package application.configuration;

import application.transformer.ResponseToSheetTransformer;
import application.utility.SheetsRetriever;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Configuration
public class SheetsConfiguration {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME =
            "deutsch-lernen";

    /**
     * Directory to store user credentials for this application.
     */
    private static final File DATA_STORE_DIR = new File(
            System.getProperty("user.home"), ".credentials/deutsch-lernen");

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     * <p>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/sheets.googleapis.com-java-quickstart
     */
    private static final List<String> SCOPES =
            Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     */
    private Credential authorize() throws IOException {
        // Load client secrets.
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("serviceclient.json"))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     *
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    @Bean
    public ResponseToSheetTransformer transformer() throws IOException {
        Credential credential = authorize();
        Sheets sheetsService = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
        SheetsRetriever sheetsRetriever = new SheetsRetriever(sheetsService);
        return new ResponseToSheetTransformer(sheetsRetriever);
    }
}
