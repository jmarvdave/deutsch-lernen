package application;

import application.service.DeutschLernenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {
    private final DeutschLernenService deutschLernenService;

    @Autowired
    public App(DeutschLernenService deutschLernenService) {
        this.deutschLernenService = deutschLernenService;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        deutschLernenService.run();
    }
}
