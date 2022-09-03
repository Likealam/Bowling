package com.bowling.service.impl;

import com.bowling.exception.GameAlreadyOverException;
import com.bowling.exception.IncorrectPinNumberException;

import java.util.ArrayList;
import java.util.List;

public class PlayerFrames {
    private final List<Frame> frames;
    private final String name;
    private final List<Bonus> bonuses;
    private int score = 0;
    
    public PlayerFrames(String name) {
        this.name = name;
        frames = new ArrayList<>();
        bonuses = new ArrayList<>();
        frames.add(new Frame());
    }

    //returns true if it is the last roll in a frame
    public boolean addRoll(int pins) {
        Frame frame = frames.get(frames.size() - 1);
        List<Integer> rolls = frame.getRolls();
        checkInput(rolls, pins);
        applyBonuses(pins);
        rolls.add(pins);
        return addScore(rolls, frame, pins);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    private void checkInput(List<Integer> rolls, int pins) {
        if (frames.size() == 10 && (rolls.size() == 3 || (rolls.size() == 2 && rolls.get(0) + rolls.get(1) < 10))) {
            throw new GameAlreadyOverException();
        }
        if (rolls.size() == 1 && frames.size() != 10 && rolls.get(0) + pins > 10) {
            throw new IncorrectPinNumberException(pins);
        }
    }

    private void applyBonuses(int pins) {
        for (int bonusIndex = 0; bonusIndex < bonuses.size(); ) {
            Bonus bonus = bonuses.get(bonusIndex);
            if (bonus.add(pins)) {
                score += bonus.getSum();
                frames.get(bonus.getFrameIndex()).setScore(score);
                bonuses.remove(bonusIndex);
            } else {
                bonusIndex++;
            }
        }
    }

    private boolean addScore(List<Integer> rolls, Frame frame, int pins) {
        if (rolls.size() == 3) {
            for (Integer roll : rolls) {
                score += roll;
            }
            frame.setScore(score);
            return true;
        }
        if (pins == 10) {
            if (frames.size() != 10) {
                bonuses.add(new Bonus(2, frames.size() - 1));
                frames.add(new Frame());
                return true;
            }
            return false;
        }
        if (rolls.size() == 2) {
            if (rolls.get(0) + pins >= 10) {
                if (frames.size() != 10) {
                    bonuses.add(new Bonus(1, frames.size() - 1));
                    frames.add(new Frame());
                    return true;
                }
                return false;
            }
            score += rolls.get(0) + pins;
            frame.setScore(score);
            if (frames.size() != 10) {
                frames.add(new Frame());
            }
            return true;
        }
        return false;
    }
}
