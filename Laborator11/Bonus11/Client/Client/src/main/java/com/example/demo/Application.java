package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import com.example.demo.model.Player;

@SpringBootApplication
@EnableSwagger2
public class Application {

    private static final int REQUEST_INTERVAL_MS = 1; // Adjust the interval between requests as needed
    private static final long DURATION_MS = 60000; // Duration for sending API requests in milliseconds

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8090"));
        app.run(args);

        AtomicInteger requestCounter = new AtomicInteger(0);
        long startTime = System.currentTimeMillis();

        // Create a timer to display the number of requests per minute
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int requestsPerMinute = requestCounter.getAndSet(0);
                System.out.println("API requests per minute: " + requestsPerMinute);
                System.exit(0);
            }
        }, DURATION_MS, DURATION_MS);

        // Send concurrent API requests until the desired duration has passed
        while (System.currentTimeMillis() - startTime < DURATION_MS) {
            Thread thread = new Thread(() -> {
                // Send an API request
                RestTemplate restTemplate = new RestTemplate();
                // Modify the request as needed
                ResponseEntity<Player[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/players/all", Player[].class);
                Player[] players = responseEntity.getBody();
                System.out.println("Received response: " + Arrays.toString(players));
                requestCounter.incrementAndGet();
            });

            thread.start();

            try {
                Thread.sleep(REQUEST_INTERVAL_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}