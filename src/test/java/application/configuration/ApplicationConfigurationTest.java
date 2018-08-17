package application.configuration;

import application.algorithm.AlgorithmExecutor;
import application.row.ResponseToRowsTransformer;
import application.exam.ExamWriter;
import org.junit.Test;
import org.mockito.Mock;

public class ApplicationConfigurationTest {
    @Mock
    private ResponseToRowsTransformer responseToRowsTransformer;

    @Mock
    private ExamWriter examWriter;

    @Mock
    private AlgorithmExecutor algorithmExecutor;

    @Test
    public void application() {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration(
            responseToRowsTransformer, examWriter, algorithmExecutor);

        applicationConfiguration.application();
    }
}