package com.example.base.controller;

import com.example.base.entity.CatDetails;
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
        List<CatDetails> mockCatDetailList = generateMockCatDetails();
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
        List<CatDetails> mockCatDetailList = generateMockCatDetails();
        Mockito.when(catwikiService.getCatDetailList(10)).thenThrow(new CatAPIException("3rd Party API unresponsive"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/topten")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false));
    }

    public List<CatDetails> generateMockCatDetails() {
        ArrayList<CatDetails> detailList = new ArrayList<>();
        detailList.add(CatDetails.builder().id("beng").name("bengal").build());
        detailList.add(CatDetails.builder().id("aeam").name("aeamaen").build());
        return detailList;
    }
}