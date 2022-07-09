package com.example.enigmashop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataOutOfStockException extends RuntimeException {
    public DataOutOfStockException(String message){
        super(message);
    }
}
