package com.example.demo.Game;

public class Games {

    private int totalPlayers;
    private int playerCounter;

    private String boardJson;
    private String playerPosJson = "";

    private String gameName;
    private Player[] gamePlayers;

    private int playerTurn;

    private boolean allLocked;
    private int turnProgress;
    private int playerWithRegister;

    Games(int totalPlayers, String username, String gameName, String json){
        this.gameName = gameName;
        turnProgress = -1;
        playerCounter = 0;
        boardJson = json;
        this.totalPlayers = totalPlayers;
        gamePlayers = new Player[totalPlayers];

        addPlayer(username);
    }

    public void addPlayer(String username){
        playerCounter++;
        gamePlayers[playerCounter - 1] = new Player(username);

        if(playerCounter == totalPlayers){
            turnProgress = 0;
        }
    }

    public String getGameState(int playerIdRequest){
        // returns playstate-playerpositions
        String playstate = "";

        if(playerCounter < totalPlayers){
            playstate = "WaitingForPlayersToConnect";
        }
        else if(!gamePlayers[playerIdRequest].getLocked()){
            playstate = "WaitingForYouToLock";
        }
        else if(!checkLocked()){
            playstate = "WaitingForOthersToLock";
        }
        else if(playerTurn == playerIdRequest){
            playstate = "WaitingForYouToPlayTurn";
        }
        else if(playerTurn != playerIdRequest){
            playstate = "WaitingForOthersToPlayTurn";
        }

        return playstate + "-" + playerPosJson;
    }

    public String playTurn(int playerNumberTurn, String json){
        String returnString = "ERROR";
        if(playerNumberTurn == playerTurn){
            playerPosJson = json;

            returnString = "SUCCESS";

            for (int i = totalPlayers - 1; i > -1; i--){
                if(gamePlayers[i].getNumberOfRegisters() >= turnProgress + 1){
                    playerWithRegister = i;
                    break;
                }
            }

            if(playerTurn == playerWithRegister){
                turnProgress++;

                boolean playerHas = false;
                for (int i = 0; i < totalPlayers; i++){
                    if(gamePlayers[i].getNumberOfRegisters() >= turnProgress + 1){
                        playerTurn = i;
                        playerHas = true;
                        break;
                    }
                }
                if(!playerHas){
                    turnProgress = -1;
                    for(int i = 0; i < totalPlayers; i++){
                        gamePlayers[i].setLocked(0, false);
                    }
                    allLocked = false;
                }
            }
            else{
                for(int i = playerTurn + 1; i < totalPlayers; i++){
                    if(gamePlayers[i].getNumberOfRegisters() >= turnProgress + 1){
                        playerTurn = i;
                        break;
                    }
                }
            }
        }

        return returnString;
    }

    private boolean checkLocked(){
        boolean lockFlag = true;

        for(int i = 0; i < totalPlayers; i++){
            if(!gamePlayers[i].getLocked()){
                lockFlag = false;
            }
        }

        return lockFlag;
    }

    public void setPlayerLocked(int playerNumber, int registerCount){
        gamePlayers[playerNumber].setLocked(registerCount, true);

        if(checkLocked()){
            allLocked = true;

            turnProgress = 0;

            for(int i = 0; i < totalPlayers; i++){
                if(gamePlayers[i].getNumberOfRegisters() >= turnProgress + 1){
                    playerTurn = i;
                    break;
                }
            }
        }
    }

    public int getPlayerNumber(){
        return playerCounter;
    }

    public void setPlayerPosJson(String json){
        playerPosJson = json;
    }

    public String getPlayerPosJson(){
        return playerPosJson;
    }

    public String getGameName(){
        return gameName;
    }

    public String getBoardJson(){
        return boardJson;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }
}
