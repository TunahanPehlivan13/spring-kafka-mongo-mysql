package com.zapu.property.search.controller.component;

import com.zapu.property.search.client.CategoryClientAdapter;
import com.zapu.property.search.client.LocationClientAdapter;
import com.zapu.property.search.client.dto.CategoryDto;
import com.zapu.property.search.client.dto.CityDto;
import com.zapu.property.search.finder.IdFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendlyURLComponent {

    private final IdFinder<CategoryDto> categoryIdFinder;
    private final IdFinder<CityDto> cityIdFinder;
    private final LocationClientAdapter locationClientAdapter;
    private final CategoryClientAdapter categoryClientAdapter;

    public CityDto findCityById(Long cityId) {
        Iterable<CityDto> cities = locationClientAdapter.findAll();
        return cityIdFinder.find(cityId, cities);
    }

    public CategoryDto findCategoryById(Long categoryId) {
        Iterable<CategoryDto> categories = categoryClientAdapter.findAll();
        return categoryIdFinder.find(categoryId, categories);
    }
}
