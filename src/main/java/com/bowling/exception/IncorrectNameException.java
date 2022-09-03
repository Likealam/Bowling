package com.bowling.exception;

public class IncorrectNameException extends RuntimeException {
    public IncorrectNameException() {
        super("Entered name is incorrect!");
    }
}
