package com.omni.webapp.controller;

import com.omni.webapp.models.APIErrorResponse;
import com.omni.webapp.models.Tag;
import javassist.bytecode.stackmap.TypeData;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestAPIErrorResponse {
    private final Logger logger = LoggerFactory.getLogger(TypeData.ClassName.class);

    @ExceptionHandler(value = TagNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<APIErrorResponse> resolveException(TagNotFoundException ex, HttpServletRequest request){
        APIErrorResponse apiErrorResponse = new APIErrorResponse(HttpStatus.BAD_REQUEST,
                "404", "Invalid request input", "Please enter a valid EMV Tag.");
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
