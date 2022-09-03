package com.bowling.service.impl;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final List<Integer> rolls;
    private int score = -1;

    public Frame() {
        rolls = new ArrayList<>();
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
