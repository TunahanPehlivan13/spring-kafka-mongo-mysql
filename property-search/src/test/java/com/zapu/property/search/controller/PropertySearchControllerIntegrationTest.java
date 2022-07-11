package com.zapu.property.search.controller;

import com.zapu.property.search.client.dto.CategoryDto;
import com.zapu.property.search.client.dto.CityDto;
import com.zapu.property.search.controller.component.FriendlyURLComponent;
import com.zapu.property.search.controller.component.PageablePropertyResponseComponent;
import com.zapu.property.search.service.PropertySearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertySearchController.class)
class PropertySearchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FriendlyURLComponent friendlyURLComponent;

    @MockBean
    private PageablePropertyResponseComponent pageablePropertyResponseComponent;

    @MockBean
    private PropertySearchService propertySearchService;

    @Test
    void shouldReturn301WhenCalledDirectSearchURLIfHasLessThanTwoCitiesInTheRequest() throws Exception {
        CategoryDto category = new CategoryDto();
        category.setId(1L);
        category.setName("category");

        CityDto city = new CityDto();
        city.setId(1L);
        city.setName("city");

        when(friendlyURLComponent.findCategoryById(1L)).thenReturn(category);
        when(friendlyURLComponent.findCityById(1L)).thenReturn(city);

        when(propertySearchService.search("category", Optional.of("city"), 1))
                .thenReturn(Page.empty());

        mockMvc.perform(get("/arama")
                        .param("category", "1")
                        .param("city", "1")
                        .param("pae", "1"))
                .andDo(print())
                .andExpect(status().isMovedPermanently());
    }

    @Test
    void shouldReturn200WhenCalledDirectSearchURLIfHasMoreThanOneCityInTheRequest() throws Exception {
        mockMvc.perform(get("/arama")
                        .param("page", "1")
                        .param("category", "1")
                        .param("city", "1")
                        .param("city", "2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnFirst5RecordsWhenCalledDirectSearchURLIfNotPassedPageParam() throws Exception {
        mockMvc.perform(get("/arama")
                        .param("category", "1")
                        .param("city", "1")
                        .param("city", "2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn200WhenCalledFriendlyCategorySearchURL() throws Exception {
        mockMvc.perform(get("/arama/konut"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn200WhenCalledFriendlyCategoryAndCitySearchURL() throws Exception {
        mockMvc.perform(get("/arama/konut/antalya"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}