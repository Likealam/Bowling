package com.bowling.service;

import com.bowling.exception.GameAlreadyOverException;
import com.bowling.exception.IncorrectNameException;
import com.bowling.exception.IncorrectPinNumberException;
import com.bowling.exception.NoGameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BowlingServiceTests {
    @Autowired
    private BowlingService bowlingService;

    @Test
    public void scoreTest() {
        List<String> players = new ArrayList<>();
        players.add("Danylo");
        bowlingService.setNewGame(players);
        bowlingService.addRoll(3);
        Assertions.assertEquals(-1, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(0).getScore());
        Assertions.assertEquals(0, bowlingService.getScoreboard().getPlayerModels().get(0).getScore());
        bowlingService.addRoll(3);
        Assertions.assertEquals(6, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(0).getScore());
        Assertions.assertEquals(6, bowlingService.getScoreboard().getPlayerModels().get(0).getScore());
        bowlingService.addRoll(3);
        Assertions.assertEquals(-1, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(1).getScore());
        Assertions.assertEquals(6, bowlingService.getScoreboard().getPlayerModels().get(0).getScore());
        bowlingService.addRoll(3);
        Assertions.assertEquals(12, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(1).getScore());
        Assertions.assertEquals(12, bowlingService.getScoreboard().getPlayerModels().get(0).getScore());
    }

    @Test
    public void twoPlayersTest() {
        List<String> players = new ArrayList<>();
        players.add("Danylo");
        players.add("Oleksa");
        bowlingService.setNewGame(players);
        bowlingService.addRoll(1);
        bowlingService.addRoll(2);
        bowlingService.addRoll(3);
        Assertions.assertEquals(3, bowlingService.getScoreboard().getPlayerModels().get(1).getFrames().get(0).getRolls().get(0));
        Assertions.assertEquals(0, bowlingService.getScoreboard().getPlayerModels().get(1).getScore());
        bowlingService.addRoll(4);
        Assertions.assertEquals(4, bowlingService.getScoreboard().getPlayerModels().get(1).getFrames().get(0).getRolls().get(1));
        Assertions.assertEquals(7, bowlingService.getScoreboard().getPlayerModels().get(1).getScore());
        Assertions.assertEquals(1, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(0).getRolls().get(0));
        Assertions.assertEquals(2, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(0).getRolls().get(1));
        Assertions.assertEquals(3, bowlingService.getScoreboard().getPlayerModels().get(0).getScore());
    }

    @Test
    public void consecutiveStrikesTest() {
        List<String> players = new ArrayList<>();
        players.add("Danylo");
        bowlingService.setNewGame(players);
        Assertions.assertEquals(-1, bowlingService.addRoll(10).getPlayerModels().get(0).getFrames().get(0).getScore());
        Assertions.assertEquals(-1, bowlingService.addRoll(10).getPlayerModels().get(0).getFrames().get(0).getScore());
        Assertions.assertEquals(30, bowlingService.addRoll(10).getPlayerModels().get(0).getFrames().get(0).getScore());
    }

    @Test
    public void strikeTest() {
        List<String> players = new ArrayList<>();
        players.add("Danylo");
        bowlingService.setNewGame(players);
        Assertions.assertEquals(-1, bowlingService.addRoll(10).getPlayerModels().get(0).getFrames().get(0).getScore());
        Assertions.assertEquals(-1, bowlingService.addRoll(5).getPlayerModels().get(0).getFrames().get(0).getScore());
        Assertions.assertEquals(20, bowlingService.addRoll(5).getPlayerModels().get(0).getFrames().get(0).getScore());
    }

    @Test
    public void spareTest() {
        List<String> players = new ArrayList<>();
        players.add("Danylo");
        bowlingService.setNewGame(players);
        Assertions.assertEquals(-1, bowlingService.addRoll(5).getPlayerModels().get(0).getFrames().get(0).getScore());
        Assertions.assertEquals(-1, bowlingService.addRoll(5).getPlayerModels().get(0).getFrames().get(0).getScore());
        Assertions.assertEquals(20, bowlingService.addRoll(10).getPlayerModels().get(0).getFrames().get(0).getScore());
    }

    @Test
    public void lastFrameStrikeTest() {
        List<String> players = new ArrayList<>();
        players.add("Danylo");
        bowlingService.setNewGame(players);
        for (int i = 0; i < 18; i++) {
            bowlingService.addRoll(0);
        }
        bowlingService.addRoll(10);
        Assertions.assertEquals(-1, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(9).getScore());
        bowlingService.addRoll(1);
        Assertions.assertEquals(-1, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(9).getScore());
        bowlingService.addRoll(1);
        Assertions.assertEquals(12, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(9).getScore());
        Assertions.assertThrows(GameAlreadyOverException.class, () -> bowlingService.addRoll(1));
    }

    @Test
    public void lastFrameSpareTest() {
        List<String> players = new ArrayList<>();
        players.add("Danylo");
        bowlingService.setNewGame(players);
        for (int i = 0; i < 18; i++) {
            bowlingService.addRoll(0);
        }
        bowlingService.addRoll(5);
        bowlingService.addRoll(5);
        Assertions.assertEquals(-1, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(9).getScore());
        bowlingService.addRoll(1);
        Assertions.assertEquals(11, bowlingService.getScoreboard().getPlayerModels().get(0).getFrames().get(9).getScore());
        Assertions.assertThrows(GameAlreadyOverException.class, () -> bowlingService.addRoll(1));
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void noGameTest() {
        Assertions.assertThrows(NoGameException.class, () -> bowlingService.getScoreboard());
        Assertions.assertThrows(NoGameException.class, () -> bowlingService.addRoll(10));
    }

    @Test
    public void gameOverTest() {
        List<String> players = new ArrayList<>();
        players.add("Danylo");
        bowlingService.setNewGame(players);
        for (int i = 0; i < 20; i++) {
            bowlingService.addRoll(0);
        }
        Assertions.assertThrows(GameAlreadyOverException.class, () -> bowlingService.addRoll(0));
    }

    @Test
    public void incorrectNameTest() {
        List<String> players = new ArrayList<>();
        players.add(" ");
        Assertions.assertThrows(IncorrectNameException.class, () -> bowlingService.setNewGame(players));
    }

    @Test
    public void incorrectPinNumberTest() {
        List<String> list = new ArrayList<>();
        list.add("Danylo");
        bowlingService.setNewGame(list);
        Assertions.assertThrows(IncorrectPinNumberException.class, () -> bowlingService.addRoll(11));
        Assertions.assertThrows(IncorrectPinNumberException.class, () -> bowlingService.addRoll(-1));
        bowlingService.addRoll(5);
        Assertions.assertThrows(IncorrectPinNumberException.class, () -> bowlingService.addRoll(6));
    }
}
