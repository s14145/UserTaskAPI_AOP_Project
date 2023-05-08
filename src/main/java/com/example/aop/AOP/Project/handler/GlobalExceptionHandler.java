package com.example.aop.AOP.Project.handler;

import com.example.aop.AOP.Project.error.ErrorMessage;
import com.example.aop.AOP.Project.exception.TaskException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ErrorMessage> handleTaskException(TaskException taskException){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setMessage(taskException.getMessage());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException noSuchElementException){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setMessage(noSuchElementException.getMessage());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(ConstraintViolationException constraintViolationException){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setMessage(constraintViolationException.getMessage());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
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