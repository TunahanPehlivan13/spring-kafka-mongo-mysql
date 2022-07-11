package com.zapu.property.viewer.controller;

import com.zapu.property.viewer.converter.PropertyConverter;
import com.zapu.property.viewer.model.PropertyDocument;
import com.zapu.property.viewer.service.PropertyViewerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertyViewerController.class)
class PropertyViewerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyViewerService propertyViewerService;

    @MockBean
    private PropertyConverter propertyConverter;

    @Test
    void shouldReturn200WhenCalledGetPropertyIfPropertyExist() throws Exception {
        PropertyDocument property = new PropertyDocument();
        property.setId("1");

        when(propertyViewerService.findById("1")).thenReturn(Optional.of(property));

        mockMvc.perform(get("/detay/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404WhenCalledGetPropertyIfPropertyDoesNotExist() throws Exception {
        when(propertyViewerService.findById("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/detay/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}