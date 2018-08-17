package application.google;

import application.google.CredentialAuthorizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class CredentialAuthorizerTest {
    @Test(expected = FileNotFoundException.class)
    public void throwExceptionWhenFileNotFound() throws IOException {
        CredentialAuthorizer credentialAuthorizer = new CredentialAuthorizer("incorrectlocation.json");

        credentialAuthorizer.authorize();
    }
}