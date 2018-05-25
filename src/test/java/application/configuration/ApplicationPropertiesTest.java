package application.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationPropertiesTest {

    @Test
    public void getDateReturnsNullWhenNotSet() {
        ApplicationProperties applicationProperties = new ApplicationProperties();

        String date = applicationProperties.getDate();

        assertThat(date).isNull();
    }
}