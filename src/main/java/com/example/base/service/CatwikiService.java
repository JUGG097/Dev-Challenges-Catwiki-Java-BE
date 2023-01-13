package com.example.base.service;

import com.example.base.entity.CatBreeds;
import com.example.base.entity.CatDetails;
import com.example.base.entity.CatImage;
import com.example.base.error.CatAPIException;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CatwikiService {
    public CatDetails getCatDetail(String catId) throws CatAPIException;

    public List<CatDetails> getCatDetailList(Number count) throws CatAPIException;

    public List<CatBreeds> getCatBreeds() throws CatAPIException;

    public List<CatImage> getCatImages(String catId, Number count) throws CatAPIException;
}
