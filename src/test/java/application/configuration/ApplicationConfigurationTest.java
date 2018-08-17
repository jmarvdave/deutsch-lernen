package application.configuration;

import application.algorithm.AlgorithmExecutor;
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
    private AlgorithmExecutor algorithmExecutor;

    @Test
    public void application() {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration(responseToSheetTransformer, latexWriter, algorithmExecutor);

        applicationConfiguration.application();
    }
}