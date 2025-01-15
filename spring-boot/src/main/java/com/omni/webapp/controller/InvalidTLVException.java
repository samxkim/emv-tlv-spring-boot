package com.omni.webapp.controller;

public class InvalidTLVException extends RuntimeException{
    public InvalidTLVException(String errorMessage) {
        super(errorMessage);
    }
}
