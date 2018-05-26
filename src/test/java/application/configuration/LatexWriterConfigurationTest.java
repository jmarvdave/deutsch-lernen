package application.configuration;

import application.utility.DateRetriever;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LatexWriterConfigurationTest {

    @Mock
    private DateRetriever dateRetriever;

    @Test
    public void latexWriter() {
        when(dateRetriever.getDate()).thenReturn("23-03-1992");

        LatexWriterConfiguration latexWriterConfiguration = new LatexWriterConfiguration(dateRetriever);

        latexWriterConfiguration.latexWriter();
    }
}