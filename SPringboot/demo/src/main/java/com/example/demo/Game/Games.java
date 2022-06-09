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
    private int playerWithRegister;

    private boolean boardUploaded;

    Games(int totalPlayers, String username, String gameName){
        this.gameName = gameName;
        playerCounter = 0;
        gameLive = false;
        boardUploaded = false;
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

    public void setPlayerLocked(int playerNumber, int registerCount){
        gamePlayers[playerNumber].setLocked(registerCount, true);
        boolean lockFlag = true;

        for(int i = 0; i < totalPlayers; i++){
            if(!gamePlayers[i].getLocked()){
                lockFlag = false;
            }
        }

        if(lockFlag){
            turnProgress = 0;

            for(int i = 0; i < totalPlayers; i++){
                if(gamePlayers[i].getNumberOfRegisters() >= turnProgress){
                    playerTurn = i;
                    break;
                }
            }
        }

        allLocked = lockFlag;
    }

    private void startGame(){
        if(boardUploaded){
            gameLive = true;
            turnProgress = -1;
            allLocked = false;
        }
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
