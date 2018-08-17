package application.configuration;

import application.algorithm.AlgorithmExecutor;
import application.service.DeutschLernenService;
import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private final ResponseToSheetTransformer transformer;
    private final LatexWriter latexWriter;
    private final AlgorithmExecutor algorithmExecutor;

    @Autowired
    public ApplicationConfiguration(ResponseToSheetTransformer transformer,
        LatexWriter latexWriter,
        AlgorithmExecutor algorithmExecutor) {
        this.transformer = transformer;
        this.latexWriter = latexWriter;
        this.algorithmExecutor = algorithmExecutor;
    }

    @Bean
    public DeutschLernenService application() {
        return new DeutschLernenService(transformer, latexWriter, algorithmExecutor);
    }
}
