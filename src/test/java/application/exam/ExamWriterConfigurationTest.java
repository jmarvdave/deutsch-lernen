package application.exam;

import static org.mockito.Mockito.when;

import application.exam.DateRetriever;
import application.exam.ExamWriterConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExamWriterConfigurationTest {

    @Mock
    private DateRetriever dateRetriever;

    @Test
    public void latexWriter() {
        when(dateRetriever.getDate()).thenReturn("23-03-1992");

        ExamWriterConfiguration examWriterConfiguration = new ExamWriterConfiguration(dateRetriever);

        examWriterConfiguration.latexWriter();
    }
}