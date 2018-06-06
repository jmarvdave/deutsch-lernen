package application.configuration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CredentialAuthorizerTest {
    @Test
    public void checkServiceAccountScope() throws IOException {
        CredentialAuthorizer credentialAuthorizer = CredentialAuthorizer.createWithDefaultCredentiaLocation();

        Credential authorize = credentialAuthorizer.authorize();

        assertThat(((GoogleCredential) authorize).getServiceAccountScopesAsString()).isEqualTo("https://www.googleapis.com/auth/spreadsheets.readonly");
    }

    @Test(expected = FileNotFoundException.class)
    public void throwExceptionWhenFileNotFound() throws IOException {
        CredentialAuthorizer credentialAuthorizer = new CredentialAuthorizer("incorrectlocation.json");

        credentialAuthorizer.authorize();
    }
}