package com.example.aop.AOP.Project.exception;

import com.example.aop.AOP.Project.error.ErrorMessage;
import com.example.aop.AOP.Project.handler.ErrorResponse;
import com.example.aop.AOP.Project.handler.TaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler{


    @ExceptionHandler(TaskException.class)
    public ResponseEntity<String> handleTaskException(TaskException taskException){
        return new ResponseEntity<String>("Task with given id is not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException){
        return new ResponseEntity<String>("Please provide valid task id.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex){


        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( err -> {
            String fieldName =  ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            errorMap.put(fieldName, message);
         });

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}


