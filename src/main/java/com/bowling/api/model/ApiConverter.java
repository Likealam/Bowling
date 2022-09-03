package com.bowling.api.model;

import com.bowling.service.impl.Frame;
import com.bowling.service.impl.PlayerFrames;
import com.bowling.service.impl.ScoreboardHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApiConverter {
    public ScoreboardModel convert(ScoreboardHandler scoreboardHandler) {
        List<PlayerFrames> playerFramesList = scoreboardHandler.getPlayerFramesList();
        List<PlayerModel> playerModels = new ArrayList<>(playerFramesList.size());
        for (PlayerFrames playerFrames : playerFramesList) {
            List<Frame> frames = playerFrames.getFrames();
            List<FrameModel> frameModels = new ArrayList<>(frames.size());
            for (Frame frame : frames) {
                frameModels.add(new FrameModel(frame.getRolls(), frame.getScore()));
            }
            playerModels.add(new PlayerModel(playerFrames.getName(), frameModels, playerFrames.getScore()));
        }
        return new ScoreboardModel(playerModels);
    }
}
