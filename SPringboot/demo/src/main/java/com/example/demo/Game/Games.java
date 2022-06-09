package com.example.demo.Game;

public class Games {
    private int totalPlayers;
    private int playerCounter;

    private String boardJson = "";
    private String playerPosJson = "";

    private String gameName;
    private Player[] gamePlayers;

    private int playerTurn;

    private boolean allLocked;
    private boolean gameLive;
    private int turnProgress;
    private int playerWithRegister;

    private boolean boardUploaded;

    Games(int totalPlayers, String username, String gameName, String json){
        this.gameName = gameName;
        playerCounter = 0;
        gameLive = false;
        boardJson = json;
        this.totalPlayers = totalPlayers;
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

        return playstate + playerPosJson;
    }

    public String playTurn(int playerNumberTurn, String json){
        String returnString = "ERROR";
        if(playerNumberTurn == playerTurn){
            boardJson = json;

            returnString = "SUCCESS";

            for (int i = totalPlayers; i > -1; i--){
                if(gamePlayers[i].getNumberOfRegisters() >= turnProgress){
                    playerWithRegister = i;
                    break;
                }
            }

            if(playerTurn == playerWithRegister){
                turnProgress++;

                boolean playerHas = false;
                for (int i = 0; i < totalPlayers; i++){
                    if(gamePlayers[i].getNumberOfRegisters() >= turnProgress){
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
                if(gamePlayers[i].getNumberOfRegisters() >= turnProgress){
                    playerTurn = i;
                    break;
                }
            }
        }
    }

    private void startGame(){
            gameLive = true;
            turnProgress = -1;
            allLocked = false;
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

    public void setBoardJson(String json){
        boardJson = json;
    }

    public void uploadBoardJson(String json){
        setBoardJson(json);
        boardUploaded = true;
    }

    public String getBoardJson(){
        return boardJson;
    }

    public boolean getAllLocked(){
        return allLocked;
    }
}
