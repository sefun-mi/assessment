package com.assessment.api_design.common.exception;

import com.assessment.api_design.common.response.WebResponseBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception exception){
        if(exception instanceof ResponseStatusException){
            ResponseStatusException statEx = (ResponseStatusException) exception;
            return WebResponseBuilder.buildFailureResponse(statEx.getReason(), statEx.getStatusCode());
        }

        if(exception instanceof MethodArgumentNotValidException){
            Map<String, String> errors = new HashMap<>();
            MethodArgumentNotValidException argumentEx = (MethodArgumentNotValidException) exception;
            argumentEx.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return WebResponseBuilder.buildResponse("Invalid request",
                    false,
                    errors,
                    HttpStatusCode.valueOf(400));

        }

        return WebResponseBuilder.buildFailureResponse(exception.getMessage(), HttpStatusCode.valueOf(500));

    }
}