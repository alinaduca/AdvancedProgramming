package com.example.demo;

import com.example.demo.model.Player;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class AppActuator {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8090"));
        app.run(args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RestController
    @ConditionalOnProperty(prefix = "management.endpoint.metrics", name = "enabled", havingValue = "true")
    public class PlayersController {
        @Autowired
        private MeterRegistry meterRegistry;

        @GetMapping("/players/all")
        public ResponseEntity<Player[]> getAllPlayers() {
            // Increment the counter for API requests
            Counter.builder("api.requests.total")
                    .tag("endpoint", "/players/all")
                    .register(meterRegistry)
                    .increment();

            // Your existing logic to fetch all players
            RestTemplate restTemplate = restTemplate();
            ResponseEntity<Player[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/players/all", Player[].class);
            Player[] players = responseEntity.getBody();

            return ResponseEntity.ok(players);
        }
    }
}
