package application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class ApplicationProperties {
    @Value("${app.date}")
    private String date;

    String getDate() {
        return date;
    }
}
