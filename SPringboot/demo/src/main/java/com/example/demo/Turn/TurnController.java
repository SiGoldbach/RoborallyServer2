package com.example.demo.Turn;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
@Component
@RestController
@RequestMapping(path = "turn")
public class TurnController {
    @GetMapping(value = "init/{playerAmountInGame}")
    public void initializer(@PathVariable String PlayerAmountInGame){


    }

    @PostMapping(value = "{playerId}")
    public void sendMoves(@PathVariable String playerId){


    }
}
