package com.assessment.api_design.common.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class WebResponseBuilder {
    public static ResponseEntity<Object> buildSuccessResponse(Object data){
        WebResponse webResponse = new WebResponse("Successful",true,data);
        return ResponseEntity.ok(webResponse);
    }

    public static ResponseEntity<Object> buildFailureResponse(String message, HttpStatusCode httpStatusCode){
        WebResponse webResponse = new WebResponse(message,false,null);
        return new ResponseEntity<Object>(webResponse,httpStatusCode);
    }

    public static ResponseEntity<Object> buildResponse(String message, boolean success,Object data, HttpStatusCode httpStatusCode){
        WebResponse webResponse = new WebResponse(message,success,data);
        return new ResponseEntity<Object>(webResponse,httpStatusCode);
    }
}