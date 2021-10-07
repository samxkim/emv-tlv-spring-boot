package com.omni.webapp.controller;

import com.omni.webapp.models.APIErrorResponse;
import javassist.bytecode.stackmap.TypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestAPIErrorResponse {
    private final Logger logger = LoggerFactory.getLogger(TypeData.ClassName.class);


    @ExceptionHandler(value = {UserNotFoundException.class, TagNotFoundException.class, InvalidTLVException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<APIErrorResponse> resolveServiceException(Exception ex, HttpServletRequest request) {
        APIErrorResponse apiErrorResponse = new APIErrorResponse(HttpStatus.BAD_REQUEST,
                "400", ex.getMessage(), "Please enter valid input.");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<APIErrorResponse> resolveExistingUserException(InvalidTLVException ex, HttpServletRequest request) {
        APIErrorResponse apiErrorResponse = new APIErrorResponse(HttpStatus.BAD_REQUEST,
                "400", ex.getMessage(), "Please enter valid user details.");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<APIErrorResponse> globalException(Exception ex, WebRequest request) {
        APIErrorResponse apiErrorResponse = new APIErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "56", new Date().toString(), ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
