package com.example.demo.Game;

public class Games {
    private int totalPlayers;
    private int playerCounter;

    private String boardJson = "";

    private String gameName;
    private Player[] gamePlayers;

    private int playerTurn;

    private boolean allLocked;
    private boolean gameLive;
    private int turnProgress;

    Games(int totalPlayers, String username, String gameName){
        gameName = this.gameName;
        playerCounter = 0;
        gameLive = false;
        totalPlayers = this.totalPlayers;
        gamePlayers = new Player[totalPlayers];

        addPlayer(username);
    }

    public void addPlayer(String username){
        gamePlayers[playerCounter] = new Player(username);
        playerCounter++;

        if(totalPlayers == playerCounter){
            startGame();
        }
    }

    public void setPlayerLocked(int playerNumber, int registerCount){
        gamePlayers[playerNumber].setLocked(registerCount);
        boolean lockFlag = true;

        for(int i = 0; i < totalPlayers; i++){
            if(!gamePlayers[i].getLocked()){
                lockFlag = false;
            }
        }

        if(lockFlag){
            turnProgress = 0;
        }

        allLocked = lockFlag;
    }

    private void startGame(){
        gameLive = true;
        turnProgress = -1;
        allLocked = false;
    }

    public int getPlayerNumber(){
        return playerCounter;
    }

    public String getGameName(){
        return gameName;
    }

    public void setBoardJson(String json){
        boardJson = json;
    }

    public String getBoardJson(){
        return boardJson;
    }

    public boolean getAllLocked(){
        return allLocked;
    }
}
