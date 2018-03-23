package application.configuration;

import com.google.api.services.sheets.v4.Sheets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private final Sheets service;

    @Autowired
    public ApplicationConfiguration(Sheets service) {
        this.service = service;
    }

    @Bean
    public DeutschLernenService application() {
        return new DeutschLernenService(service);
    }
}
