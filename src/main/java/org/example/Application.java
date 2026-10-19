package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// TÁTO ANOTÁCIA JE KÚZLO: Povie Springu, aby naštartoval celú aplikáciu a načítal application.properties
public class Application {
    public static void main(String[] args) {
        // Tento jeden riadok spustí celý Spring Boot engine na pozadí
        SpringApplication.run(Application.class, args);
    }
}
