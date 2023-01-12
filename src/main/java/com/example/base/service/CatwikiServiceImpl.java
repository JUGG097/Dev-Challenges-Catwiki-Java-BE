package com.example.base.service;

import com.example.base.entity.CatBreeds;
import com.example.base.entity.CatDetails;
import com.example.base.entity.CatImage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CatwikiServiceImpl implements CatwikiService{
    @Override
    public CatDetails getCatDetails(String fetchKey) {
        return null;
    }

    @Override
    public Object[] getCatBreeds() {
        var uri = "https://api.thecatapi.com/v1/breeds/";
//        RestTemplate restTemplate = new RestTemplate();
//        Object[] breedlist = restTemplate
        WebClient client = WebClient.builder()
                .baseUrl(uri)
                .defaultHeader("x-api-key",
                        "live_MhXwjew5ges6i6eWXn9CDk56UX08g37L8A3nkPnrlLcDPouOitL2bChxOHDoBvOF")
                .build();
        Mono<Object[]> response = client.get().retrieve().bodyToMono(Object[].class);
        return response.block();
    }

    @Override
    public List<CatImage> getCatImages() {
        return null;
    }
}
