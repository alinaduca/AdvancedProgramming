package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import com.example.demo.model.Player;
import com.example.demo.model.Game;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8090"));
        app.run(args);

        // Create a new player
        Player newPlayer = new Player(7, "Bacinschi", 27);
        ResponseEntity<Player> createdPlayerResponse = new RestTemplate()
                .postForEntity("http://localhost:8080/players/add-player", newPlayer, Player.class);
        Player createdPlayer = createdPlayerResponse.getBody();
        System.out.println("Created Player: " + createdPlayer);

        Player newPlayer1 = new Player(8, "P8", 28);
        ResponseEntity<Player> createdPlayerResponse1 = new RestTemplate().postForEntity("http://localhost:8080/players/add-player", newPlayer1, Player.class);
        Player newPlayer2 = new Player(9, "P9", 29);
        ResponseEntity<Player> createdPlayerResponse2 = new RestTemplate().postForEntity("http://localhost:8080/players/add-player", newPlayer2, Player.class);

        // Update the name of the player
        String newName = "P2 Updated";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>("" + newName + "", headers);
        ResponseEntity<Player> updatedPlayerResponse = new RestTemplate()
                .exchange("http://localhost:8080/players/{id}", HttpMethod.PUT, requestEntity, Player.class,
                        2);
        Player updatedPlayer = updatedPlayerResponse.getBody();
        System.out.println("Updated Player: " + updatedPlayer);

        // Delete the player
        new RestTemplate()
                .delete("http://localhost:8080/players/delete/{id}", 1);

        // Get all players
        ResponseEntity<Player[]> playersResponse = new RestTemplate()
                .getForEntity("http://localhost:8080/players/all", Player[].class);
        Player[] players = playersResponse.getBody();
        System.out.println("Players: " + Arrays.toString(players));

        // Get all games
        ResponseEntity<Game[]> gamesResponse = new RestTemplate()
                .getForEntity("http://localhost:8080/games/all", Game[].class);
        Game[] games = gamesResponse.getBody();
        System.out.println("Games: " + Arrays.toString(games));
    }
}