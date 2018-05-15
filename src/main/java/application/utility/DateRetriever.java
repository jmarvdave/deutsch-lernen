package application.utility;

import application.configuration.ApplicationProperties;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRetriever {
    private final ApplicationProperties applicationProperties;

    public DateRetriever(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public String getDate() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        if (!"none".equals(applicationProperties.getDate())) {
            String[] parseDate = applicationProperties.getDate().split("-");
            date = LocalDate.of(Integer.valueOf(parseDate[0]),
                    Integer.valueOf(parseDate[1]),
                    Integer.valueOf(parseDate[2]))
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }

        return date;
    }
}
