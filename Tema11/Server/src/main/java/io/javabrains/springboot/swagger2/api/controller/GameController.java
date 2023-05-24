package io.javabrains.springboot.swagger2.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.javabrains.springboot.swagger2.service.GameService;
import io.javabrains.springboot.swagger2.api.model.Game;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService = new GameService();

    @GetMapping("/all")
    public ResponseEntity<List<Game>> getGames() {
        List<Game> games;
        try {
            games = gameService.getGames();
        } catch(NullPointerException e) {
            games = null;
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
}