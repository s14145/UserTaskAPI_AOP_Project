package com.example.aop.AOP.Project.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private LocalDateTime timestamp;
    private String message;
    private int status;
}