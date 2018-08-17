package application.exam;

import application.configuration.ApplicationProperties;
import application.exam.DateRetriever;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DateRetrieverTest {
    @Mock
    private ApplicationProperties applicationProperties;

    private DateRetriever dateRetriever;

    @Before
    public void setUp() {
        dateRetriever = new DateRetriever(applicationProperties);
    }

    @Test
    public void getDate() {
        when(applicationProperties.getDate()).thenReturn("2023-04-02");

        String date = dateRetriever.getDate();

        assertThat(date).isEqualTo("02-04-2023");
    }

    @Test
    public void fallbackToTodayWhenDateEntered() {
        when(applicationProperties.getDate()).thenReturn("none");

        String date = dateRetriever.getDate();

        String[] parsedDate = date.split("-");

        assertThat(parsedDate.length).isEqualTo(3);
    }
}