package com.assessment.api_design.common.exception;

import com.assessment.api_design.common.response.WebResponseBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception exception){
        if(exception instanceof ResponseStatusException){
            ResponseStatusException statEx = (ResponseStatusException) exception;
            return WebResponseBuilder.buildFailureResponse(statEx.getMessage(), statEx.getStatusCode());
        }

        //todo add for validation exception

        return WebResponseBuilder.buildFailureResponse(exception.getMessage(), HttpStatusCode.valueOf(500));

    }
}