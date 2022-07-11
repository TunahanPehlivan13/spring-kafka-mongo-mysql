package com.zapu.location.controller;

import com.zapu.location.converter.CityConverter;
import com.zapu.location.model.City;
import com.zapu.location.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
class CityControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @MockBean
    private CityConverter cityConverter;

    @Test
    public void shouldReturn200WhenCalledGetCities() throws Exception {
        // given
        City adanaCity = new City();
        adanaCity.setId(1L);
        adanaCity.setName("name");

        Iterable<City> cities = Set.of(adanaCity);

        // when
        when(cityService.findAll()).thenReturn(cities);

        // then
        mockMvc.perform(get("/api/cities"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}