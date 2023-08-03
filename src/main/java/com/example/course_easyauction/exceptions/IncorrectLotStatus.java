package com.example.course_easyauction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectLotStatus extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Лот в неверном статусе";

    public IncorrectLotStatus() {
        super(DEFAULT_MESSAGE);
    }
}
