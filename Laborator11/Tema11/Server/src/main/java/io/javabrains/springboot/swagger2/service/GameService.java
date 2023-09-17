package io.javabrains.springboot.swagger2.service;

import io.javabrains.springboot.swagger2.api.model.Game;
import io.javabrains.springboot.swagger2.api.model.Player;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private PlayerService playerService;

    private final List<Game> gameList;

    public GameService() {
        gameList = new ArrayList<>();
    }

    public List<Game> getGames() {
        List<Player> players = null;
        try {
            players = playerService.getPlayers();
        } catch (NullPointerException e) { }
        playerService = new PlayerService();
        gameList.clear();
        for(int i = 0; i < players.size(); i+=2) {
            if(i < players.size() - 1) {
                Game game = new Game(i / 2 + 1, players.get(i), players.get(i + 1));
                gameList.add(game);
            }
        }
        return gameList;
    }
}