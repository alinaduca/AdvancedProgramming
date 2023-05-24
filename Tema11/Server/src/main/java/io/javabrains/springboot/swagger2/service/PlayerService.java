package io.javabrains.springboot.swagger2.service;

import io.javabrains.springboot.swagger2.api.model.Player;
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
        playerList.addAll(Arrays.asList(player1, player2, player3, player4));
    }

    public Player getPlayer(Integer id) {
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
