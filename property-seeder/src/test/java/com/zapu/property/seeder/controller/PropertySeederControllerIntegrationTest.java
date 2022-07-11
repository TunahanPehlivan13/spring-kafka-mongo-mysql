package com.zapu.property.seeder.controller;

import com.zapu.property.seeder.service.PropertySeederService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertySeederController.class)
class PropertySeederControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PropertySeederService propertySeederService;
    
    @Test
    void shouldReturn200WhenCalledPostProperty() throws Exception {
        mockMvc.perform(post("/api/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"categoryId\": 1,\n" +
                                "    \"cityId\": 1,\n" +
                                "    \"title\": \"title\",\n" +
                                "    \"currency\": \"TL\",\n" +
                                "    \"price\": 1.0\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400WhenCalledPostPropertyIfCategoryIdIsNull() throws Exception {
        mockMvc.perform(post("/api/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"cityId\": 1,\n" +
                                "    \"title\": \"title\",\n" +
                                "    \"currency\": \"TL\",\n" +
                                "    \"price\": 1.0\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenCalledPostPropertyIfCityIdIsNull() throws Exception {
        mockMvc.perform(post("/api/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"categoryId\": 1,\n" +
                                "    \"title\": \"title\",\n" +
                                "    \"currency\": \"TL\",\n" +
                                "    \"price\": 1.0\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenCalledPostPropertyIfTitleIsNull() throws Exception {
        mockMvc.perform(post("/api/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"categoryId\": 1,\n" +
                                "    \"cityId\": 1,\n" +
                                "    \"currency\": \"TL\",\n" +
                                "    \"price\": 1.0\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenCalledPostPropertyIfCurrencyIsNull() throws Exception {
        mockMvc.perform(post("/api/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"categoryId\": 1,\n" +
                                "    \"cityId\": 1,\n" +
                                "    \"title\": \"title\",\n" +
                                "    \"price\": 1.0\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenCalledPostPropertyIfPriceIsNull() throws Exception {
        mockMvc.perform(post("/api/property")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"categoryId\": 1,\n" +
                                "    \"cityId\": 1,\n" +
                                "    \"title\": \"title\",\n" +
                                "    \"currency\": \"TL\"\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}