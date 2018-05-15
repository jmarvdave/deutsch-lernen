package application.configuration;

import application.writer.LatexWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class LatexWriterConfiguration {
    private final ApplicationProperties applicationProperties;

    public LatexWriterConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public LatexWriter latexWriter() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        if (!"none".equals(applicationProperties.getDate())) {
            String[] parseDate = applicationProperties.getDate().split("-");
            date = LocalDate.of(Integer.valueOf(parseDate[0]),
                    Integer.valueOf(parseDate[1]),
                    Integer.valueOf(parseDate[2]))
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }

        return new LatexWriter(date);
    }
}

