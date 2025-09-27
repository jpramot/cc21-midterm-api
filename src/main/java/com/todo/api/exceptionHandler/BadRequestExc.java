package com.todo.api.exceptionHandler;

public class BadRequestExc extends RuntimeException{
    public BadRequestExc(String message) {
        super(message);
    }
}
