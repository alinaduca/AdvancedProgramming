package com.example.demo.service;

import com.example.demo.api.model.Player;
import org.springframework.stereotype.Service;
import java.util.*;
import static java.lang.Math.max;

@Service
public class PlayerGroupService {
    private List<Player> players;
    private Map<Player, List<Player>> graph;
    public PlayerGroupService() {
        players = PlayerService.getPlayers();
        graph = new HashMap<>();
        buildGraph();
    }

    public List<Player> minimalGroup() {
        Random random = new Random();
        List<Player> groupC = new ArrayList<>(players);
        List<Player> groupA = new ArrayList<>();
        List<Player> groupB = new ArrayList<>();
        List<Player> exGroupC;
        boolean canBeAsigned;
        do {
            List<Player> neighborsA = new ArrayList<>();
            canBeAsigned = true;
            exGroupC = new ArrayList<>(groupC);
            Player player = groupC.get(random.nextInt(groupC.size()));
            for(Integer opponent : player.getOpponents()) {
                Player opp = PlayerService.getPlayer(opponent);
                neighborsA.add(opp);
                if(groupB.contains(opp)) {
                    canBeAsigned = false;
                    break;
                }
            }
            if(canBeAsigned) {
                groupA.add(player);
                groupC.remove(player);
                for (Player opp : neighborsA) {
                    if(!groupB.contains(opp)) {
                        groupB.add(opp);
                        groupC.remove(opp);
                    }
                }
            }
        } while(groupC.size() > 0 || max(groupA.size(), groupB.size()) < 2 * (groupA.size() + groupB.size()) / 3);
        if(max(groupA.size(), groupB.size()) >= 2 * (groupA.size() + groupB.size()) / 3) {
            return exGroupC;
        }
        return groupC;
    }

    private void buildGraph() {
        for(Player player : players) {
            List<Player> neighbors = new ArrayList<>();
            for(Integer player1 : player.getOpponents()) {
                neighbors.add(PlayerService.getPlayer(player1));
            }
            graph.put(player, neighbors);
        }
    }
}