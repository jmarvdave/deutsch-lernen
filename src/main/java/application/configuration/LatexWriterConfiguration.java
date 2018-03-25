package application.configuration;

import application.utility.LatexWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LatexWriterConfiguration {

    @Bean
    public LatexWriter latexWriter() {
        return new LatexWriter();
    }
}

