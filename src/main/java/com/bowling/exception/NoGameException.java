package com.bowling.exception;

public class NoGameException extends RuntimeException {
    public NoGameException() {
        super("Rolled before starting the game!");
    }
}
