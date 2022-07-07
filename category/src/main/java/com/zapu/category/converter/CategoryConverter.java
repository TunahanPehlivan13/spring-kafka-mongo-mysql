package com.zapu.category.converter;

import com.zapu.category.controller.response.CategoryResponse;
import com.zapu.category.model.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CategoryConverter implements Converter<Category, CategoryResponse> {

    @Override
    public CategoryResponse convert(Category source) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(source.getCategoryId());
        categoryResponse.setName(source.getName());
        return categoryResponse;
    }

    @Override
    public Iterable<CategoryResponse> convert(Iterable<Category> sources) {
        return StreamSupport.stream(sources.spliterator(), false)
                .map(this::convert)
                .collect(Collectors.toUnmodifiableSet());
    }
}
