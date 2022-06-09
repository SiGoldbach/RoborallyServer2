package com.example.demo.Game;

import com.example.demo.board.BoardDataLayer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RestController
@RequestMapping(path="greeting")

public class AnnounceConnectionController {

    private int counter = 0;
    private int gameCounter = -1;

    private Games[] myGames = new Games[10];

    @GetMapping(value="/games/{name}")
    public int saveUser(@RequestBody String data, @PathVariable String name) throws IOException, URISyntaxException {
        // Data is hosting(true or false)-gamename-playerCount-username
        String[] dataArray = data.split("-");

        return gameCounter++;
    }
}


