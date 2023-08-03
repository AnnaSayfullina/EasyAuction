package com.example.course_easyauction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectLotInformation extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Лот передан с ошибкой";

    public IncorrectLotInformation() {
        super(DEFAULT_MESSAGE);
    }
}
