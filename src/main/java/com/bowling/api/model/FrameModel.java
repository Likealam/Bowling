package com.bowling.api.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FrameModel {
    private final List<Integer> rolls;
    private final Integer score;

    public FrameModel(List<Integer> rolls, Integer score) {
        this.rolls = Collections.unmodifiableList(rolls);
        this.score = score;
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameModel that = (FrameModel) o;
        return score.equals(that.score) && rolls.equals(that.rolls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolls, score);
    }
}
