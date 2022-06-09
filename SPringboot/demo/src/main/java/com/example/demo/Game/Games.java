package com.example.demo.Game;

public class Games {
    private int totalPlayers;
    private int playerCounter;

    private Player[] gamePlayers;

    Games(int totalPlayers, String username){
        totalPlayers = this.totalPlayers;
        gamePlayers = new Player[totalPlayers];

        addPlayer(username);
    }

    private void addPlayer(String username){
        gamePlayers[playerCounter] = new Player(username);
    }
}
