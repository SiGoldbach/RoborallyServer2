package com.example.demo.Game;

public class Player {
    private String username;
    private int numberOfRegisters;
    private boolean locked;

    Player(String username){
        username = this.username;
        locked = false;
    }

    public void setLocked(int numberOfRegisters){
        numberOfRegisters = this.numberOfRegisters;
        locked = true;
    }

    public boolean getLocked(){
        return locked;
    }
}
