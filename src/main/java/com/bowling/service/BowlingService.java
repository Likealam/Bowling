package com.bowling.service;

import com.bowling.api.model.ScoreboardModel;

import java.util.List;


public interface BowlingService {
    void setNewGame(List<String> players);

    ScoreboardModel addRoll(Integer score);

    ScoreboardModel getScoreboard();
}
