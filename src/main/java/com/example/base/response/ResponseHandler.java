package com.example.base.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
@ResponseStatus
@ResponseBody
public class ResponseHandler {
    public ResponseEntity<Object> generateResponse(Boolean success, HttpStatus status, Object responseObj) {

        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("success", success);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map, status);

    }

    public ResponseEntity<Object> generateResponse(String message, HttpStatus status) {

        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("message", message);
        map.put("status", status.value());

        return new ResponseEntity<Object>(map, status);
    }
}
