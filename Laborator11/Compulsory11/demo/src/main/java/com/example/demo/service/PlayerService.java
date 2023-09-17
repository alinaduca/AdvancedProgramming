package com.example.demo.service;

import com.example.demo.api.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private List<Player> playerList;

    public PlayerService() {
        playerList = new ArrayList<>();
        Player player1 = new Player(1, "P1", 21);
        Player player2 = new Player(2, "P2", 22);
        Player player3 = new Player(3, "P3", 23);
        Player player4 = new Player(4, "P4", 24);
        playerList.addAll(Arrays.asList(player1, player2, player3, player4));
    }

    public Optional<Player> getPlayer(Integer id) {
        Optional optional = Optional.empty();
        for(Player player : playerList) {
            if(id.equals(player.getId())) {
                optional = Optional.of(player);
                return optional;
            }
        }
        return optional;
    }

    public List<Player> getPlayers() {
        return playerList;
    }
}
