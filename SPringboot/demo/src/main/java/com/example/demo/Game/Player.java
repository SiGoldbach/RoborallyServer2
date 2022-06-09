package com.example.demo.Game;

public class Player {
    private String username;
    private int numberOfRegisters;
    private boolean locked;

    Player(String username){
        this.username = username;
        locked = false;
    }

    public void setLocked(int numberOfRegisters, boolean lockedData){
        this.numberOfRegisters = numberOfRegisters;
        locked = lockedData;
    }

    public boolean getLocked(){
        return locked;
    }

    public int getNumberOfRegisters(){
        return numberOfRegisters;
    }
}
