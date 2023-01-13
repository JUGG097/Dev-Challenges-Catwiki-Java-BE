package com.example.base.controller;

import com.example.base.error.CatAPIException;
import com.example.base.response.ResponseHandler;
import com.example.base.service.CatwikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(endpointPrefix + "/topten")
    public ResponseEntity<Object> getTopTen() throws CatAPIException {
        var payload = catwikiService.getCatDetailList(10);

        return responseHandler.generateResponse(true, HttpStatus.OK, payload);
    }

    @GetMapping(endpointPrefix + "/details/{catId}")
    public ResponseEntity<Object> getCatDetail(@PathVariable String catId) throws CatAPIException {
        var detailPayload = catwikiService.getCatDetail(catId);

        var imagePayload = catwikiService.getCatImages(catId, 1);

        detailPayload.setImage(imagePayload.get(0));

        return responseHandler.generateResponse(true, HttpStatus.OK, detailPayload);
    }

    @GetMapping(endpointPrefix + "/photos/{catId}")
    public ResponseEntity<Object> getCatPhotos(@PathVariable String catId) throws CatAPIException {
        var imagePayload = catwikiService.getCatImages(catId, 8);

        return responseHandler.generateResponse(true, HttpStatus.OK, imagePayload);
    }

    @GetMapping(endpointPrefix + "/breedlist")
    public ResponseEntity<Object> getCatBreeds() throws CatAPIException {
        var payload = catwikiService.getCatBreeds();

        return responseHandler.generateResponse(true, HttpStatus.OK, payload);
    }
}
