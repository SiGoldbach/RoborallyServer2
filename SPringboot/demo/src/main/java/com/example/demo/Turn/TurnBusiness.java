package com.example.demo.Turn;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TurnBusiness {
    private Turn turn;

    public List<Turn.cardsList> getFullTurn(){
        return turn.listOfPlayers;
    }
}
