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
        // Data is = hosting(true or false)-gamename-username-playercount
        String[] dataArray = data.split("-");

        String hosting = dataArray[0];
        String gameName = dataArray[1].toLowerCase();
        String userName = dataArray[2];
        int returnNumber = -1;
        if(hosting.equals("true")){
            int totalPlayers = Integer.parseInt(dataArray[3]);

            gameCounter++;
            myGames[gameCounter] = new Games(totalPlayers, userName, gameName);
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

        return returnString.toString();
    }

    @PostMapping(value="/play")
    public String playGame(@RequestBody String data){
        // Data is = gamename-gamenumber-playernumber-whatdo-bigdata
        String[] dataArray = data.split("-");

        String gameName = dataArray[0].toLowerCase();
        int gameNumber = Integer.parseInt(dataArray[1]);
        String playerNumber = dataArray[2];
        String whatdo = dataArray[3];

        String returnString = "ERROR";
        if(myGames[gameNumber].getGameName().equals(gameName)){
            Games myGame = myGames[gameNumber];
            switch(whatdo){
                case "uploadBoard":
                    String boardJsonUpload = dataArray[4];
                    myGame.setBoardJson(boardJsonUpload);
                    returnString = "SUCCESS";
                case "playturn":
                    String boardJson = dataArray[4];
                    myGame.playTurn(playerNumber, boardJson);
                    break;
                case "refresh":
            }
        }

        return returnString;
    }
}


