package com.zapu.category.controller;

import com.zapu.category.converter.CategoryConverter;
import com.zapu.category.model.Category;
import com.zapu.category.service.CategoryService;
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

@WebMvcTest(CategoryController.class)
class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryConverter categoryConverter;

    @Test
    void shouldReturn200WhenCalledGetCategories() throws Exception {
        // given
        Category arsa = new Category();
        arsa.setCategoryId(1L);
        arsa.setName("category");

        Iterable<Category> categories = Set.of(arsa);

        // when
        when(categoryService.findAll()).thenReturn(categories);

        // then
        mockMvc.perform(get("/api/categories"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}