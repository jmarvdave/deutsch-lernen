package application.configuration;

import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class ApplicationConfigurationTest {

    @Mock
    private ResponseToSheetTransformer responseToSheetTransformer;

    @Mock
    private LatexWriter latexWriter;

    @Test
    public void application() {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration(responseToSheetTransformer, latexWriter);

        applicationConfiguration.application();
    }
}