package com.example.base.service;

import com.example.base.entity.CatBreeds;
import com.example.base.entity.CatDetails;
import com.example.base.entity.CatImage;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CatwikiService {
    public CatDetails getCatDetails(String fetchKey);

    public Object[] getCatBreeds();

    public List<CatImage> getCatImages();
}
