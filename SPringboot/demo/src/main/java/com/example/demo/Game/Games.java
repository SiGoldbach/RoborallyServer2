package com.example.demo.Game;

public class Games {
    private int totalPlayers;
    private int playerCounter;

    private String gameName;
    private Player[] gamePlayers;

    private int playerTurn;

    private boolean allLocked;
    private boolean gameLive;

    Games(int totalPlayers, String username, String gameName){
        gameName = this.gameName;
        gameLive = false;
        totalPlayers = this.totalPlayers;
        gamePlayers = new Player[totalPlayers];

        addPlayer(username);
    }

    private void addPlayer(String username){
        gamePlayers[playerCounter] = new Player(username);
        playerCounter++;

        if(totalPlayers == playerCounter){
            startGame();
        }
    }

    public void setPlayerLocked(){
        boolean lockFlag = true;

        for(int i = 0; i < totalPlayers; i++){
            if(!gamePlayers[i].getLocked()){
                lockFlag = false;
            }
        }

        allLocked = lockFlag;
    }

    private void startGame(){
        gameLive = true;
        allLocked = false;
    }

    public int getPlayerNumber(){
        return playerCounter;
    }
}
