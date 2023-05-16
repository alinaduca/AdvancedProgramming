package org.example;

public class Player {
    private String name;
    private char symbol;
    private ClientThread client;
    private Player oponent;
    private long timeLeft;

    public Player(String name, ClientThread client) {
        this.name = name;
        this.client = client;
        symbol = '?';
    }
    public Player() {
    }

    public void updateTime (long timeSpent)
    {
        this.timeLeft = this.timeLeft - timeSpent;
    }
    public void setOpponent(Player oponent) {
        this.oponent = oponent;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setTime (long time)
    {
        this.timeLeft = time;
    }

    public long getTime ()
    {
        return timeLeft;
    }

    public boolean equals (Player p2)
    {
        if(name == p2.getName())
            return true;
        return false;
    }
}
