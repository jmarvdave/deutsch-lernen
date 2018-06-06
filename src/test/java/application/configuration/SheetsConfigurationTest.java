package application.configuration;

import com.google.api.client.auth.oauth2.Credential;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SheetsConfigurationTest {

    @Mock
    private SheetsConnector sheetsConnector;
    @Mock
    private CredentialAuthorizer credentialAuthorizer;
    @Mock
    private Credential credential;

    private SheetsConfiguration sheetsConfiguration;

    @Before
    public void setUp() {
        sheetsConfiguration = new SheetsConfiguration();
    }

    @Test
    public void createsTransformerBean() throws IOException {
        when(credentialAuthorizer.authorize()).thenReturn(credential);

        sheetsConfiguration.transformer(credentialAuthorizer, sheetsConnector);
    }
}