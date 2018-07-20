package application.configuration;

import application.service.DeutschLernenService;
import application.service.ResultsWeigher;
import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private final ResponseToSheetTransformer transformer;
    private final LatexWriter latexWriter;
    private final ResultsWeigher weighter;

    @Autowired
    public ApplicationConfiguration(ResponseToSheetTransformer transformer, LatexWriter latexWriter, ResultsWeigher weighter) {
        this.transformer = transformer;
        this.latexWriter = latexWriter;
        this.weighter = weighter;
    }

    @Bean
    public DeutschLernenService application() {
        return new DeutschLernenService(transformer, latexWriter, weighter);
    }
}
