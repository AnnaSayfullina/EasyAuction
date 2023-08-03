package com.example.course_easyauction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LotNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Лот не найден";

    public LotNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
