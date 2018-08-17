package application.configuration;

import application.algorithm.AlgorithmExecutor;
import application.service.DeutschLernenService;
import application.row.ResponseToRowsTransformer;
import application.exam.ExamWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private final ResponseToRowsTransformer transformer;
    private final ExamWriter examWriter;
    private final AlgorithmExecutor algorithmExecutor;

    @Autowired
    public ApplicationConfiguration(ResponseToRowsTransformer transformer,
        ExamWriter examWriter,
        AlgorithmExecutor algorithmExecutor) {
        this.transformer = transformer;
        this.examWriter = examWriter;
        this.algorithmExecutor = algorithmExecutor;
    }

    @Bean
    public DeutschLernenService application() {
        return new DeutschLernenService(transformer, examWriter, algorithmExecutor);
    }
}
