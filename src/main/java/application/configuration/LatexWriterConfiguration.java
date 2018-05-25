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
        this(new DateRetriever(applicationProperties));
    }

    public LatexWriterConfiguration(DateRetriever dateRetriever) {
        this.dateRetriever = dateRetriever;
    }

    @Bean
    public LatexWriter latexWriter() {
        return new LatexWriter(dateRetriever.getDate());
    }
}

