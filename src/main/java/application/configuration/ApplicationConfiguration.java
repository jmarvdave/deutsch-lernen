package application.configuration;

import application.service.DeutschLernenService;
import application.utility.LatexWriter;
import com.google.api.services.sheets.v4.Sheets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private final Sheets service;
    private final LatexWriter latexWriter;

    @Autowired
    public ApplicationConfiguration(Sheets service, LatexWriter latexWriter) {
        this.service = service;
        this.latexWriter = latexWriter;
    }

    @Bean
    public DeutschLernenService application() {
        return new DeutschLernenService(service, latexWriter);
    }
}
