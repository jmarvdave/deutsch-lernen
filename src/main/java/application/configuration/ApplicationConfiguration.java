package application.configuration;

import application.service.DeutschLernenService;
import application.writer.LatexWriter;
import application.transformer.ResponseToSheetTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private final ResponseToSheetTransformer transformer;
    private final LatexWriter latexWriter;

    @Autowired
    public ApplicationConfiguration(ResponseToSheetTransformer transformer, LatexWriter latexWriter) {
        this.transformer = transformer;
        this.latexWriter = latexWriter;
    }

    @Bean
    public DeutschLernenService application() {
        return new DeutschLernenService(transformer, latexWriter);
    }
}
