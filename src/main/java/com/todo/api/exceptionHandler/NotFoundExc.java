package com.todo.api.exceptionHandler;

import lombok.Getter;

@Getter
public class NotFoundExc extends RuntimeException{

    private  String  resource = "data";

    public NotFoundExc(String resource, String fieldName, String value) {
        super(String.format("%s not found with %s: %s", resource, fieldName, value));
        this.resource = resource;
    }

    public NotFoundExc(String resource, String fieldName, long value) {
        super(String.format("%s not found with %s: %d", resource, fieldName, value));
        this.resource = resource;
    }

    public NotFoundExc(String message) {
        super(message);
    }
}
