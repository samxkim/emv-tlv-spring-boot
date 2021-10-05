package com.omni.webapp.models;

import org.springframework.http.HttpStatus;

public class APIErrorResponse {
    private HttpStatus status;
    private String errorCode;
    private String message;
    private String detail;

    public APIErrorResponse(HttpStatus status, String errorCode, String message, String detail) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}