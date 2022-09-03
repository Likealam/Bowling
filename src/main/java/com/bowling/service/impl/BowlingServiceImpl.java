package com.bowling.service.impl;

import com.bowling.api.model.ApiConverter;
import com.bowling.api.model.ScoreboardModel;
import com.bowling.exception.NoGameException;
import com.bowling.service.BowlingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BowlingServiceImpl implements BowlingService {
    private final ApiConverter apiConverter;
    private ScoreboardHandler scoreboardHandler;

    public BowlingServiceImpl(ApiConverter apiConverter) {
        this.apiConverter = apiConverter;
    }

    @Override
    public ScoreboardModel addRoll(Integer score) {
        if (scoreboardHandler == null) {
            throw new NoGameException();
        }
        scoreboardHandler.addRoll(score);
        return apiConverter.convert(scoreboardHandler);
    }

    @Override
    public ScoreboardModel getScoreboard() {
        if (scoreboardHandler == null) {
            throw new NoGameException();
        }
        return apiConverter.convert(scoreboardHandler);
    }

    @Override
    public void setNewGame(List<String> players) {
        scoreboardHandler = new ScoreboardHandler(players);
    }
}
