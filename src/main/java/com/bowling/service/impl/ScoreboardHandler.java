package com.bowling.service.impl;

import com.bowling.exception.IncorrectNameException;
import com.bowling.exception.IncorrectPinNumberException;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardHandler {
    private final List<PlayerFrames> playerFramesList;
    private int playerIndex = 0;

    ScoreboardHandler(List<String> players) {
        playerFramesList = new ArrayList<>();
        checkPlayerNames(players);
        for (String player : players) {
            playerFramesList.add(new PlayerFrames(player));
        }
    }

    public void addRoll(int pins) {
        if (pins > 10 || pins < 0) {
            throw new IncorrectPinNumberException(pins);
        }
        if (playerFramesList.get(playerIndex).addRoll(pins)) {
            playerIndex = (playerIndex + 1) % playerFramesList.size();
        }
    }

    public List<PlayerFrames> getPlayerFramesList() {
        return playerFramesList;
    }

    private void checkPlayerNames(List<String> players) {
        for (String player : players) {
            if (player.isBlank()) {
                throw new IncorrectNameException();
            }
        }
    }
}
