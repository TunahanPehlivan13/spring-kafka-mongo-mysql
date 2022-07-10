package com.zapu.category.controller;

import com.zapu.category.converter.CategoryConverter;
import com.zapu.category.model.Category;
import com.zapu.category.service.CategoryService;
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

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @SpyBean
    private CategoryConverter categoryConverter;

    @Test
    void shouldGetCategories() throws Exception {
        // given
        Category arsa = new Category();
        arsa.setCategoryId(1L);
        arsa.setName("arsa");

        Category ticari = new Category();
        ticari.setCategoryId(2L);
        ticari.setName("ticari");

        Category konut = new Category();
        konut.setCategoryId(3L);
        konut.setName("konut");

        Iterable<Category> categories = Set.of(arsa, ticari, konut);

        // when
        when(categoryService.findAll()).thenReturn(categories);

        // then
        mockMvc.perform(get("/api/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", hasItem(1)))
                .andExpect(jsonPath("$[*].name", hasItem("arsa")))
                .andExpect(jsonPath("$[*].id", hasItem(2)))
                .andExpect(jsonPath("$[*].name", hasItem("konut")))
                .andExpect(jsonPath("$[*].id", hasItem(3)))
                .andExpect(jsonPath("$[*].name", hasItem("ticari")));
    }
}