package com.bowling.exception;

public class IncorrectPinNumberException extends RuntimeException {
    IncorrectPinNumberException() {
        super("Inputted pin number is incorrect!");
    }

    public IncorrectPinNumberException(int pinNumber) {
        super("Inputted pin number " + pinNumber + " is incorrect!");
    }
}
