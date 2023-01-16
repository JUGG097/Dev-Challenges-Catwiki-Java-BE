package com.example.base.controller;

import com.example.base.entity.CatBreeds;
import com.example.base.entity.CatDetails;
import com.example.base.entity.CatImage;
import com.example.base.error.CatAPIException;
import com.example.base.service.CatwikiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CatwikiController.class)
class CatwikiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatwikiService catwikiService;


    @Test
    void getTopTenSucceeds() throws Exception {
        var mockCatDetailList = generateMockCatDetails();
        Mockito.when(catwikiService.getCatDetailList(10)).thenReturn(mockCatDetailList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/topten")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id")
                        .value(mockCatDetailList.get(0).getId()));
    }

    @Test
    void getTopTenFails() throws Exception {
        Mockito.when(catwikiService.getCatDetailList(10))
                .thenThrow(new CatAPIException("3rd Party API unresponsive"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/topten")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false));
    }

    @Test
    void getCatDetailSucceeds() throws Exception {
        var mockCatDetailList = generateMockCatDetails();
        var mockCatImageList = generateMockCatImages();

        Mockito.when(catwikiService.getCatDetail("beng")).thenReturn(mockCatDetailList.get(0));

        Mockito.when(catwikiService.getCatImages("beng", 1)).thenReturn(mockCatImageList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/details/beng")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id")
                        .value(mockCatDetailList.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.image.id")
                        .value(mockCatImageList.get(0).getId()));
    }

    @Test
    void getCatDetailFailure() throws Exception {
        Mockito.when(catwikiService.getCatDetail("beng"))
                .thenThrow(new CatAPIException("3rd Party API unresponsive"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/details/beng")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("3rd Party API unresponsive"));
    }

    @Test
    void getCatPhotosSucceeds() throws Exception {
        var mockCatImageList = generateMockCatImages();
        Mockito.when(catwikiService.getCatImages("beng", 8)).thenReturn(mockCatImageList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/photos/beng")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id")
                        .value(mockCatImageList.get(0).getId()));
    }

    @Test
    void getCatPhotosFailure() throws Exception {
        Mockito.when(catwikiService.getCatImages("beng", 8))
                .thenThrow(new CatAPIException("3rd party API unresponsive"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/photos/beng")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("3rd party API unresponsive"));
    }

    @Test
    void getCatBreedSucceeds() throws Exception {
        var mockCatBreedsList = generateMockCatBreeds();
        Mockito.when(catwikiService.getCatBreeds()).thenReturn(mockCatBreedsList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/breedlist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id")
                        .value(mockCatBreedsList.get(0).getId()));
    }

    @Test
    void getCatBreedFailure() throws Exception {
        Mockito.when(catwikiService.getCatBreeds()).thenThrow(new CatAPIException("3rd party API unresponsive"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/breedlist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("3rd party API unresponsive"));
    }

    public List<CatDetails> generateMockCatDetails() {
        ArrayList<CatDetails> detailList = new ArrayList<>();
        detailList.add(CatDetails.builder().id("beng").name("bengal").build());
        detailList.add(CatDetails.builder().id("aeam").name("aeamaen").build());
        return detailList;
    }

    public List<CatImage> generateMockCatImages() {
        ArrayList<CatImage> detailList = new ArrayList<>();
        detailList.add(CatImage.builder().id("qwerty123").build());
        detailList.add(CatImage.builder().id("asdfg456").build());
        return detailList;
    }

    public List<CatBreeds> generateMockCatBreeds() {
        ArrayList<CatBreeds> detailList = new ArrayList<>();
        detailList.add(CatBreeds.builder().id("beng").name("bengal").build());
        detailList.add(CatBreeds.builder().id("aeam").name("aeamean").build());
        return detailList;
    }
}