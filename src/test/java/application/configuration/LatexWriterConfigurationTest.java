package application.configuration;

import application.utility.DateRetriever;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

        try {
            String fileName = "testwrite.tex";
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            assertThat(lines.contains("\\question machen")).isTrue();
            assertThat(lines.contains("\\question Mann")).isTrue();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}