package com.example.aop.AOP.Project.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ErrorMessage {

    private LocalDateTime timestamp;
    private String message;
    private int status;
}