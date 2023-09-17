package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int timeLimit;
    private int currentPlayerIndex;

    private long startTime;

    public Game(int boardSize) {
        this.board = new Board(boardSize);
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.timeLimit = 3000000;
    }
    public List<Player> getPlayers() {
        return players;
    }

    public boolean addPlayer(Player player) {
        if (players.size() >= 2) {
            return false;
        }
        players.add(player);
        if (players.size() == 2) {
            players.get(0).setOpponent(players.get(1));
            players.get(1).setOpponent(players.get(0));
            players.get(1).setSymbol('*');
            players.get(0).setTime(timeLimit);
            players.get(1).setTime(timeLimit);
            startTime = System.currentTimeMillis();
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public boolean makeMove(int row, int col, Player player, boolean timeOut) {
        if (!player.equals(getCurrentPlayer())) {
            return false;
        }
        if (!board.isEmpty(row, col)) {
            return false;
        }
        long now = System.currentTimeMillis();
        long timeSpent = now - startTime;
        player.updateTime(timeSpent);
        if(player.getTime()>0) {
            board.setCell(row, col, player.getSymbol());
            board.printCells();
            switchPlayer();
            timeOut = false;
            return true;
        }
        else
        {
            timeOut = true;
            return false;
        }
    }


    public String getStatus(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(board.toStringForResponse());
        sb.append("Timp ramas pentru ");
        sb.append(players.get(0).getName());
        sb.append(": ");
        sb.append(players.get(0).getTime());
        sb.append("@");
        sb.append("Timp ramas pentru ");
        sb.append(players.get(1).getName());
        sb.append(": ");
        sb.append(players.get(1).getTime());
        sb.append("@");
        if (player.equals(getCurrentPlayer())) {
            if(player.getTime()<=0)
            {
                sb.append("Timpul tau a expirat@");
            }
            else {
                sb.append("Randul tau@");
            }
        } else {
            sb.append("Astepta ca ");
            sb.append(getCurrentPlayer().getName());
            sb.append(" sa faca o mutare@");
        }
        return sb.toString();
    }


    public boolean isWinner(char symbol) {
        char[][] cells = board.getCells();
        int n = cells.length;

        // Check rows
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (cells[i][j] == symbol) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check columns
        for (int j = 0; j < n; j++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (cells[i][j] == symbol) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check diagonals from top-left to bottom-right
        for (int k = 0; k < n; k++) {
            int count = 0;
            for (int i = k, j = 0; i < n && j < n; i++, j++) {
                if (cells[i][j] == symbol) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        for (int k = 1; k < n; k++) {
            int count = 0;
            for (int i = 0, j = k; i < n && j < n; i++, j++) {
                if (cells[i][j] == symbol) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }

        // Check diagonals from top-right to bottom-left
        for (int k = n - 1; k >= 0; k--) {
            int count = 0;
            for (int i = 0, j = k; i < n && j >= 0; i++, j--) {
                if (cells[i][j] == symbol) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        for (int k = n - 2; k >= 0; k--) {
            int count = 0;
            for (int i = k, j = n - 1; i >= 0 && j >= 0; i--, j--) {
                if (cells[i][j] == symbol) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }
}
