package com.example.aop.AOP.Project.error;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -8770115558265218321L;

    private int code;

    private String status;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}