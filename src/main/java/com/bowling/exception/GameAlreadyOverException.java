package com.bowling.exception;

public class GameAlreadyOverException extends RuntimeException {
    public GameAlreadyOverException() {
        super("Game is already over!");
    }
}
