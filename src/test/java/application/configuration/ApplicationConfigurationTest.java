package application.configuration;

import application.service.ResultsWeigher;
import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;
import org.junit.Test;
import org.mockito.Mock;

public class ApplicationConfigurationTest {

    @Mock
    private ResponseToSheetTransformer responseToSheetTransformer;

    @Mock
    private LatexWriter latexWriter;

    @Mock
    private ResultsWeigher resultsWeigher;

    @Test
    public void application() {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration(responseToSheetTransformer, latexWriter, resultsWeigher);

        applicationConfiguration.application();
    }
}