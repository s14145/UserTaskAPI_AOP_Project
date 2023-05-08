package com.example.aop.AOP.Project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) - This can be used instead of HttpStatus variable here
public class TaskException extends RuntimeException {

    private String message;
    public HttpStatus httpStatus;

    public TaskException(){
        super();
    }

    public TaskException(String message){
        super(message);
    }

    public TaskException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public TaskException(Throwable cause){
        super(cause);
    }

    public TaskException(String message, Throwable cause){
        super(message, cause);
    }

    public TaskException(String message, Throwable cause, HttpStatus httpStatus){
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}