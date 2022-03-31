package com.example.aop.AOP.Project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND) - This can be used instead of HttpStatus variable here
public class TaskException extends RuntimeException {

    private String message;
    public HttpStatus httpStatus;

    public TaskException(){
        super();
    }

    public TaskException(String message){
        super();
        this.message = message;
    }

    public TaskException(HttpStatus httpStatus,String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public TaskException(Throwable cause){
        super(cause);
    }

    public TaskException(HttpStatus httpStatus, String message, Throwable cause){
        super(cause);
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
