package com.omni.webapp.controller;

import com.omni.webapp.models.APIErrorResponse;
import javassist.bytecode.stackmap.TypeData;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestAPIErrorResponse {
    private final Logger logger = LoggerFactory.getLogger(TypeData.ClassName.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public APIErrorResponse resolveException(HttpMessageNotReadableException ex, HttpServletRequest request){
        //logError("Bad Request", ex, request)
        return new APIErrorResponse(HttpStatus.BAD_REQUEST,
                "404", "Invalid request input", ExceptionUtils.getMessage(ex));
    }

    private void logError(String message, Exception e, HttpServletRequest r) {
        //var headers = r.headerNames.asSequence()
        //        .map { it to r.getHeader(it) }
        //    .toMap()
        logger.error("$message for ${r.requestURI} with headers: $headers", e);
    }
}
