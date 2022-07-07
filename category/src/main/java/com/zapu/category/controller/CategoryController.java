package com.zapu.category.controller;

import com.zapu.category.controller.response.CategoryResponse;
import com.zapu.category.converter.Converter;
import com.zapu.category.model.Category;
import com.zapu.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final Converter<Category, CategoryResponse> categoryConverter;

    @GetMapping("/api/categories")
    public Iterable<CategoryResponse> findAll() {
        Iterable<Category> categories = categoryService.findAll();
        return categoryConverter.convert(categories);
    }
}
