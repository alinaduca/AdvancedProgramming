package com.example.demo.api.controller;

import com.example.demo.api.model.Player;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getPlayers() {
        System.out.println(playerService.getPlayers());
        return new ResponseEntity<>(playerService.getPlayers(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/add-player", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        playerService.save(player);
        return new ResponseEntity<>(player, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deletePerson(@PathVariable Integer id) {
        playerService.deleteById(id);
        return new ResponseEntity<>(id, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> updatePlayer(@PathVariable Integer id, @RequestBody String newName) {
        return new ResponseEntity<>(playerService.update(id, newName), new HttpHeaders(), HttpStatus.OK);
    }
}
