package application.google;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class GoogleCredentialAuthorizerTest {
    @Test(expected = FileNotFoundException.class)
    public void throwExceptionWhenFileNotFound() throws IOException {
        GoogleCredentialAuthorizer googleCredentialAuthorizer = new GoogleCredentialAuthorizer("incorrectlocation.json");

        googleCredentialAuthorizer.authorize();
    }
}