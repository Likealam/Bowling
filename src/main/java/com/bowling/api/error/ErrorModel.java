package com.bowling.api.error;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorModel {
    NO_GAME(0, "Start a game before rolling!"),
    INCORRECT_NAME(1, "One of the entered names is incorrect!"),
    INCORRECT_PIN_NUMBER(2, "Entered pin number is incorrect!"),
    GAME_ALREADY_OVER(3, "Game is already over!");
    private final Integer code;
    private final String message;

    ErrorModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
