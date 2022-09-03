package com.bowling.controller;

import com.bowling.api.model.ScoreboardModel;
import com.bowling.service.BowlingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BowlingController {
    private final BowlingService bowlingService;

    public BowlingController(BowlingService bowlingService) {
        this.bowlingService = bowlingService;
    }

    @PostMapping(path = "new-game")
    public void setNewGame(@RequestBody List<String> players) {
        bowlingService.setNewGame(players);
    }

    @GetMapping(path = "scoreboard")
    public ScoreboardModel getScoreboard() {
        return bowlingService.getScoreboard();
    }

    @PostMapping(path = "roll/{score}")
    public ScoreboardModel newRoll(@PathVariable int score) {
        return bowlingService.addRoll(score);
    }
}
