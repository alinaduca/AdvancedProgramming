package com.example.demo.api.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String name;
    private int age;
    private List<Integer> opponents = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Player(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

//    public void playsWith(Player other) {
//        if(!opponents.contains(other)) {
//            opponents.add(other);
//        }
//    }

//    public boolean hasPlayedWith(Player other) {
//        if(opponents.contains(other)) {
//            return true;
//        }
//        return false;
//    }

    public void addOpponent(Player opponent) {
        opponents.add(opponent.getId());
    }

    public List<Integer> getOpponents() {
        return opponents;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
