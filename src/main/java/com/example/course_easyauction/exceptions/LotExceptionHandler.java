package com.example.course_easyauction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LotExceptionHandler {

    @ExceptionHandler(LotNotFoundException.class)
    public ResponseEntity<?> handleLotNotFoundException(LotNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectLotStatus.class)
    public ResponseEntity<?>  handleIncorrectLotStatus(IncorrectLotStatus e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectLotInformation.class)
    public ResponseEntity<?>  handleIncorrectLotInformation(IncorrectLotInformation e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
