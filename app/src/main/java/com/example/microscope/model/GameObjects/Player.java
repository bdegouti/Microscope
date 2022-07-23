package com.example.microscope.model.GameObjects;

public class Player {
    String name;
    int playerNum;
    public Player(String name, int playerNum){
        this.name = name;
        this.playerNum = playerNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }
}
