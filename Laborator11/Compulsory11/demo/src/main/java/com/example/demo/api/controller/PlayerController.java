package com.example.demo.api.controller;

import com.example.demo.api.model.Player;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("/player")
    public Player getPlayer(@RequestParam Integer id) {
        Optional player = playerService.getPlayer(id);
        if(player.isPresent()) {
            return (Player)player.get();
        }
        return null;
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return (List<Player>) playerService.getPlayers();
    }
}
