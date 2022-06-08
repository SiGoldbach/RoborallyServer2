package com.example.demo.Turn;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TurnBusiness {
    private int playerAmount;
    private Turn turn;

    public List<List<CardTypes>> getFullTurn() {
        return turn.listOfPlayers;
    }

    /**
     * A method for collecting moves into a final ready to send form
     */
    public void collectMoves(int playerNumber, List<CardTypes> list) {
        turn.listOfPlayers.add(playerNumber, list);

    }
}
