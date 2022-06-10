package com.example.demo.Game;

import com.example.demo.board.BoardDataLayer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RestController
@RequestMapping(path="gamehandler")
public class GameController {

    private int gameCounter = -1;

    private Games[] myGames = new Games[10];

    @PostMapping(value="/connect")
    public String connectUser(@RequestBody String data) throws IOException, URISyntaxException {
        // Data is = hosting(true or false)-gamename-username-playercount-boardjson
        String[] dataArray = data.split("-");

        String hosting = dataArray[0];
        String gameName = dataArray[1].toLowerCase();
        String userName = dataArray[2];
        int returnPlayerNumber = 99;
        int returnTotalNumber = 99;
        if(hosting.equals("true")){
            int totalPlayers = Integer.parseInt(dataArray[3]);
            String json = dataArray[4];

            gameCounter++;
            myGames[gameCounter] = new Games(totalPlayers, userName, gameName, json);
            returnPlayerNumber = 1;
            returnTotalNumber = totalPlayers;
        }
        else{
            for(int i = 0; i < gameCounter + 1; i++){
                if(myGames[i].getGameName().equals(gameName)){
                    myGames[i].addPlayer(userName);
                    returnPlayerNumber = myGames[i].getPlayerNumber();
                    returnTotalNumber = myGames[i].getTotalPlayers();
                }
            }
        }

        return gameCounter + "-" + returnPlayerNumber + "-" + returnTotalNumber + "-" + myGames[gameCounter].getBoardJson();
    }

    @PostMapping(value="/play")
    public String playGame(@RequestBody String data){
        // Data is = gamenumber-playernumber-whatdo-bigdata
        String[] dataArray = data.split("-");

        System.out.println(data);

        int gameNumber = Integer.parseInt(dataArray[0]);
        int playerNumber = Integer.parseInt(dataArray[1]);
        String whatdo = dataArray[2];

        String returnString = "ERROR";

        Games myGame = myGames[gameNumber];
            switch(whatdo){
                case "playturn":
                    String playerPos = dataArray[3];
                    returnString = myGame.playTurn(playerNumber, playerPos);
                    break;
                case "lock":
                    int registersToLock = Integer.parseInt(dataArray[3]);
                    myGame.setPlayerLocked(playerNumber, registersToLock);
                    break;
                case "refresh":
                    returnString = myGame.getGameState(playerNumber);
                    break;
            }
        return returnString;
    }

    @GetMapping(value="/games")
    public String playGame(){

        String returnString = "";

        for(int i = 0; i < gameCounter + 1; i++){
            if(i == (gameCounter - 1)){
                returnString = returnString + myGames[i].getGameName();
            }
            else{
                returnString = returnString + myGames[i].getGameName() + "-";
            }
        }


        System.out.println("Games : " + returnString);
        return returnString;
    }
}


