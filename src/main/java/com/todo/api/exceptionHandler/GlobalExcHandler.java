package com.todo.api.exceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.todo.api.dtos.responseDto.ErrorApiRes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExcHandler {

    @ExceptionHandler(NotFoundExc.class)
    public ResponseEntity<ErrorApiRes> notFoundExc(NotFoundExc err, HttpServletRequest request) {
        String path = request.getServletPath();
        ErrorApiRes response = new ErrorApiRes(err.getMessage(), "Resource not found error",path);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestExc.class)
    public ResponseEntity<ErrorApiRes> badRequestExc(BadRequestExc err, HttpServletRequest request) {
        String path = request.getServletPath();
        ErrorApiRes response = new ErrorApiRes(err.getMessage(), "Bad request error", path);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApiRes> methodArgNotValid(MethodArgumentNotValidException err, HttpServletRequest request) {
        Map<String, String> detail = new HashMap<>();
        err.getBindingResult().getFieldErrors().forEach(errItem -> {
            String field = errItem.getField();
            String value = errItem.getDefaultMessage();
            detail.put(field, value);
        });
        String path = request.getServletPath();
        ErrorApiRes response = new ErrorApiRes("Validation failed", "Validation error", detail, path);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorApiRes> noHandlerFound (NoResourceFoundException err, HttpServletRequest request) {
        String message = String.format("Path not found: %s ", err.getResourcePath());
        String path = request.getServletPath();
        ErrorApiRes response = new ErrorApiRes(message, "Path not found error", path);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorApiRes> httpMsgNotReadable(HttpMessageNotReadableException err, HttpServletRequest request) {
        String message;
        if (err.getCause() instanceof InvalidFormatException invalidExc) {
            if(!invalidExc.getPath().isEmpty()){
            message = String.format("Invalid format of %s",invalidExc.getPath().get(0).getFieldName());
            }else {
                message = "Invalid format of data";
            }
        } else if (err.getMessage().contains("not a valid `java.lang.Long` value")) {
            message = "Invalid number format";
        }else {
            message = "Invalid request format";
        }
        String path = request.getServletPath();
        ErrorApiRes response = new ErrorApiRes(message, "Invalid request error", path);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public  ResponseEntity<ErrorApiRes> badCredentials(BadCredentialsException err, HttpServletRequest request) {
        String path = request.getServletPath();
        ErrorApiRes response = new ErrorApiRes(err.getMessage(), "Authorization error", path);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    public ResponseEntity<ErrorApiRes> illegalStateException(IllegalStateException err, HttpServletRequest request) {
        String path = request.getServletPath();
        ErrorApiRes response = new ErrorApiRes(err.getMessage(), "Internal server error", path);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApiRes> globalExc(Exception err, HttpServletRequest request) {
        String path = request.getServletPath();
        ErrorApiRes response = new ErrorApiRes(err.getMessage(), "Internal server error", path);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
