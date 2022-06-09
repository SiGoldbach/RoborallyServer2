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
        int returnNumber = -1;
        if(hosting.equals("true")){
            int totalPlayers = Integer.parseInt(dataArray[3]);
            String json = dataArray[4];

            gameCounter++;
            myGames[gameCounter] = new Games(totalPlayers, userName, gameName, json);
            returnNumber = 0;
        }
        else{
            for(int i = 0; i < gameCounter; i++){
                if(myGames[i].getGameName().equals(gameName)){
                    myGames[i].addPlayer(userName);
                    returnNumber = myGames[i].getPlayerNumber();
                }
            }
        }

        StringBuilder returnString = new StringBuilder();
        returnString.append(gameCounter);
        returnString.append("-");
        returnString.append(returnNumber);
        returnString.append("-");
        returnString.append(myGames[gameCounter].getBoardJson());

        return returnString.toString();
    }

    @PostMapping(value="/play")
    public String playGame(@RequestBody String data){
        // Data is = gamename-gamenumber-playernumber-whatdo-bigdata
        String[] dataArray = data.split("-");

        String gameName = dataArray[0].toLowerCase();
        int gameNumber = Integer.parseInt(dataArray[1]);
        int playerNumber = Integer.parseInt(dataArray[2]);
        String whatdo = dataArray[3];

        String returnString = "ERROR";
        if(myGames[gameNumber].getGameName().equals(gameName)){
            Games myGame = myGames[gameNumber];
            switch(whatdo){
                case "playturn":
                    String boardJson = dataArray[4];
                    returnString = myGame.playTurn(playerNumber, boardJson);
                    break;
                case "lock":
                    int registersToLock = Integer.parseInt(dataArray[4]);
                    myGame.setPlayerLocked(playerNumber, registersToLock);
                    break;
                case "refresh":
                    returnString = myGame.getGameState(playerNumber);
                    break;
            }
        }

        return returnString;
    }
}


