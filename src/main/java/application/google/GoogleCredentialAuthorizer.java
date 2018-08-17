package application.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

public class GoogleCredentialAuthorizer {
    private final String credentialLocation;

    public GoogleCredentialAuthorizer(String credentialLocation) {
        this.credentialLocation = credentialLocation;
    }

    static GoogleCredentialAuthorizer createWithDefaultCredentiaLocation() {
        return new GoogleCredentialAuthorizer("serviceclient.json");
    }

    public Credential authorize() throws IOException {
        // Load client secrets.
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(credentialLocation))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
        return credential;
    }
}
