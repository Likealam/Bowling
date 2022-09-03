package com.bowling.controller;

import com.bowling.api.error.ErrorModel;
import com.bowling.exception.GameAlreadyOverException;
import com.bowling.exception.IncorrectNameException;
import com.bowling.exception.IncorrectPinNumberException;
import com.bowling.exception.NoGameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(GameAlreadyOverException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorModel gameAlreadyOver() {
        return ErrorModel.GAME_ALREADY_OVER;
    }

    @ExceptionHandler(IncorrectPinNumberException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorModel incorrectPinNumber() {
        return ErrorModel.INCORRECT_PIN_NUMBER;
    }

    @ExceptionHandler(NoGameException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorModel noGame() {
        return ErrorModel.NO_GAME;
    }

    @ExceptionHandler(IncorrectNameException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorModel incorrectName() {
        return ErrorModel.INCORRECT_NAME;
    }
}
