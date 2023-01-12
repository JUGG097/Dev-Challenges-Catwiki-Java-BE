package com.example.base.controller;

import com.example.base.response.ResponseHandler;
import com.example.base.service.CatwikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatwikiController {

    private final String endpointPrefix = "/api/v1";

    @Autowired
    private CatwikiService catwikiService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping("/check")
    public ResponseEntity<Object> check() {
        return responseHandler.generateResponse("Server is up and running", HttpStatus.OK);
    }

    @GetMapping(endpointPrefix + "/topTen")
    public ResponseEntity<Object> getTopTen() {
        return responseHandler.generateResponse("", HttpStatus.NOT_FOUND);
    }

    @GetMapping(endpointPrefix + "/details/{catId}")
    public ResponseEntity<Object> getCatDetail() {
        return responseHandler.generateResponse("", HttpStatus.NOT_FOUND);
    }

    @GetMapping(endpointPrefix + "/photos/{catId}")
    public ResponseEntity<Object> getCatPhotos() {
        return responseHandler.generateResponse("", HttpStatus.NOT_FOUND);
    }

    @GetMapping(endpointPrefix + "/breedlist")
    public ResponseEntity<Object> getCatBreeds() {
        var payload = catwikiService.getCatBreeds();
        return responseHandler.generateResponse(true, HttpStatus.OK, payload);
    }
}
