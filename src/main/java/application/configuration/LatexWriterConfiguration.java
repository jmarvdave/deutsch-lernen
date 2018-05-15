package application.configuration;

import application.utility.DateRetriever;
import application.writer.LatexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LatexWriterConfiguration {
    private final DateRetriever dateRetriever;

    @Autowired
    public LatexWriterConfiguration(ApplicationProperties applicationProperties) {
        this.dateRetriever = new DateRetriever(applicationProperties);
    }

    @Bean
    public LatexWriter latexWriter() {
        return new LatexWriter(dateRetriever.getDate());
    }
}

