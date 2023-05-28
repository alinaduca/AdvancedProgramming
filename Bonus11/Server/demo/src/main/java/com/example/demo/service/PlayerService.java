package com.example.demo.service;

import com.example.demo.api.model.Player;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlayerService {
    private static List<Player> playerList;

    public PlayerService() {
        playerList = new ArrayList<>();
        Player player1 = new Player(1, "P1", 21);
        Player player2 = new Player(2, "P2", 22);
        Player player3 = new Player(3, "P3", 23);
        Player player4 = new Player(4, "P4", 24);
        Player player5 = new Player(5, "P5", 25);
        Player player6 = new Player(6, "P6", 26);
        Player player7 = new Player(7, "P7", 27);
        player7.addOpponent(player5);
        player5.addOpponent(player7);
        player4.addOpponent(player1);
        player1.addOpponent(player4);
        player2.addOpponent(player3);
        player3.addOpponent(player2);
        player7.addOpponent(player1);
        player1.addOpponent(player7);
        player3.addOpponent(player7);
        player7.addOpponent(player3);
        player4.addOpponent(player2);
        player2.addOpponent(player4);
        player1.addOpponent(player6);
        player6.addOpponent(player1);
        playerList.addAll(Arrays.asList(player1, player2, player3, player4, player5, player6, player7));
    }

    public static Player getPlayer(Integer id) {
        for(Player player : playerList) {
            if(id.equals(player.getId())) {
                return player;
            }
        }
        return null;
    }

    public static List<Player> getPlayers() {
        return playerList;
    }

    public void save(Player player) {
        if(getPlayer(player.getId()) == null) {
            playerList.add(player);
        }
    }

    public Player update(Integer id, String newName) {
        Player player = getPlayer(id);
        player.setName(newName);
        return player;
    }

    public void deleteById(Integer id) {
        playerList.remove(getPlayer(id));
    }
}
