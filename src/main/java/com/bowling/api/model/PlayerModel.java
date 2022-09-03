package com.bowling.api.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayerModel {
    private final String name;
    private final List<FrameModel> frames;
    private final Integer score;

    public PlayerModel(String name, List<FrameModel> frameModels, Integer score) {
        this.name = name;
        this.frames = Collections.unmodifiableList(frameModels);
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public List<FrameModel> getFrames() {
        return frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerModel that = (PlayerModel) o;
        return name.equals(that.name) && frames.equals(that.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, frames);
    }
}
