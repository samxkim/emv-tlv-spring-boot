package com.omni.webapp.models;

import org.springframework.http.HttpStatus;

public class APIErrorResponse {
    private HttpStatus status;
    private String errorCode;
    private String message;
    private String detail;

    public APIErrorResponse(HttpStatus badRequest, String s, String invalid_request_input, String message) {

    }
}
