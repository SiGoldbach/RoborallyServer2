package com.example.demo.Game;

public class Player {
    private String username;
    private boolean locked;

    Player(String username){
        username = this.username;
        locked = false;
    }

    public boolean getLocked(){
        return locked;
    }
}
