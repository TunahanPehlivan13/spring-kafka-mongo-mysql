package com.zapu.location.controller;

import com.zapu.location.converter.CityConverter;
import com.zapu.location.model.City;
import com.zapu.location.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @SpyBean
    private CityConverter cityConverter;

    @Test
    public void shouldGetCities() throws Exception {
        // given
        City adanaCity = new City();
        adanaCity.setId(1L);
        adanaCity.setName("adana");

        City antalyaCity = new City();
        antalyaCity.setId(7L);
        antalyaCity.setName("antalya");

        City ankaraCity = new City();
        ankaraCity.setId(6L);
        ankaraCity.setName("ankara");

        Iterable<City> cities = Set.of(adanaCity, ankaraCity, antalyaCity);

        // when
        when(cityService.findAll()).thenReturn(cities);

        // then
        mockMvc.perform(get("/api/cities"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", hasItem(1)))
                .andExpect(jsonPath("$[*].name", hasItem("adana")))
                .andExpect(jsonPath("$[*].id", hasItem(7)))
                .andExpect(jsonPath("$[*].name", hasItem("antalya")))
                .andExpect(jsonPath("$[*].id", hasItem(6)))
                .andExpect(jsonPath("$[*].name", hasItem("ankara")));
    }
}