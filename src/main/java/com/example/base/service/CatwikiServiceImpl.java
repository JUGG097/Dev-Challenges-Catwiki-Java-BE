package com.example.base.service;

import com.example.base.entity.CatBreeds;
import com.example.base.entity.CatDetails;
import com.example.base.entity.CatImage;
import com.example.base.error.CatAPIException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CatwikiServiceImpl implements CatwikiService{
    @Override
    public CatDetails getCatDetail(String catId) throws CatAPIException {
        try {
            var uri = "https://api.thecatapi.com/v1/breeds/" + catId;

            WebClient client = WebClient.builder()
                    .baseUrl(uri)
                    .defaultHeader("x-api-key",
                            "live_MhXwjew5ges6i6eWXn9CDk56UX08g37L8A3nkPnrlLcDPouOitL2bChxOHDoBvOF")
                    .build();
            Mono<CatDetails> response = client.get()
                    .retrieve().bodyToMono(CatDetails.class).log();

            return response.block();
        } catch (Exception e) {
            throw new CatAPIException("3rd Party API unresponsive: " + e.getMessage());
        }

    }

    @Override
    public List<CatDetails> getCatDetailList(Number count) throws CatAPIException {
        try {
            var uri = "https://api.thecatapi.com/v1/breeds?limit=" + count.toString() + "&page=0";

            WebClient client = WebClient.builder()
                    .baseUrl(uri)
                    .defaultHeader("x-api-key",
                            "live_MhXwjew5ges6i6eWXn9CDk56UX08g37L8A3nkPnrlLcDPouOitL2bChxOHDoBvOF")
                    .build();
            Mono<List<CatDetails>> response = client.get()
                    .retrieve().bodyToMono(new ParameterizedTypeReference<List<CatDetails>>() {
                    }).log();

            return response.block();
        } catch (Exception e) {
            throw new CatAPIException("3rd Party API unresponsive: " + e.getMessage());
        }
    }

    @Override
    public List<CatBreeds> getCatBreeds() throws CatAPIException {
        try {
            var uri = "https://api.thecatapi.com/v1/breeds/";

            WebClient client = WebClient.builder()
                    .baseUrl(uri)
                    .defaultHeader("x-api-key",
                            "live_MhXwjew5ges6i6eWXn9CDk56UX08g37L8A3nkPnrlLcDPouOitL2bChxOHDoBvOF")
                    .build();
            Mono<List<CatBreeds>> response = client.get()
                    .retrieve().bodyToMono(new ParameterizedTypeReference<List<CatBreeds>>() {
                    }).log();

            return response.block();
        } catch (Exception e){
            throw new CatAPIException("3rd Party API unresponsive: " + e.getMessage());
        }
    }

    @Override
    public List<CatImage> getCatImages(String catId, Number count) throws CatAPIException {
        try {
            var uri = "https://api.thecatapi.com/v1/images/search?page=0&limit=" +
                    count.toString() +
                    "&breed_ids=" +
                    catId +
                    "&include_breeds=false";

            WebClient client = WebClient.builder()
                    .baseUrl(uri)
                    .defaultHeader("x-api-key",
                            "live_MhXwjew5ges6i6eWXn9CDk56UX08g37L8A3nkPnrlLcDPouOitL2bChxOHDoBvOF")
                    .build();
            Mono<List<CatImage>> response = client.get()
                    .retrieve().bodyToMono(new ParameterizedTypeReference<List<CatImage>>() {
                    }).log();

            return response.block();
        } catch (Exception e){
            throw new CatAPIException("3rd Party API unresponsive: " + e.getMessage());
        }
    }
}
