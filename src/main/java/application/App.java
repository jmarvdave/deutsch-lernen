package application;

import application.configuration.DeutschLernenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class App implements CommandLineRunner {
    private final DeutschLernenService deutschLernenService;

    @Autowired
    public App(DeutschLernenService deutschLernenService) {
        this.deutschLernenService = deutschLernenService;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class, args);
    }

    public void run(String... args) throws Exception {
        deutschLernenService.run();
    }
}
