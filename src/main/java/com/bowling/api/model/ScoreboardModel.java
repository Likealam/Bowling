package com.bowling.api.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ScoreboardModel {
    List<PlayerModel> players;

    public ScoreboardModel(List<PlayerModel> playerModels) {
        this.players = Collections.unmodifiableList(playerModels);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreboardModel that = (ScoreboardModel) o;
        return players.equals(that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }

    public List<PlayerModel> getPlayerModels() {
        return players;
    }
}
