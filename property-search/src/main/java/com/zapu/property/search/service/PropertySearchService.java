package com.zapu.property.search.service;

import com.zapu.property.search.client.CategoryClientAdapter;
import com.zapu.property.search.client.LocationClientAdapter;
import com.zapu.property.search.client.dto.CategoryDto;
import com.zapu.property.search.client.dto.CityDto;
import com.zapu.property.search.finder.NameFinder;
import com.zapu.property.search.model.PropertyDocument;
import com.zapu.property.search.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertySearchService {

    private static final int PAGE_SIZE = 5;

    private final PropertyRepository propertyRepository;
    private final LocationClientAdapter locationClientAdapter;
    private final CategoryClientAdapter categoryClientAdapter;
    private final NameFinder<CategoryDto> categoryNameFinder;
    private final NameFinder<CityDto> cityNameFinder;

    public Page<PropertyDocument> search(String categoryName, Optional<String> mayCityName, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);

        Long categoryId = findCategoryId(categoryName);
        if (mayCityName.isEmpty()) {
            return propertyRepository.findByCategoryId(categoryId, pageRequest);
        }
        Long cityId = findCityId(mayCityName.get());
        return propertyRepository.findByCategoryIdAndCityId(categoryId, cityId, pageRequest);
    }

    public Page<PropertyDocument> search(Long categoryId, List<Long> cityIds, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);
        return propertyRepository.findByCategoryIdAndCityIdIn(categoryId, cityIds, pageRequest);
    }

    private Long findCityId(String cityName) {
        Iterable<CityDto> cities = locationClientAdapter.getAllCities();
        return cityNameFinder.find(cityName, cities).getId();
    }

    private Long findCategoryId(String categoryName) {
        Iterable<CategoryDto> categories = categoryClientAdapter.getAll();
        return categoryNameFinder.find(categoryName, categories).getId();
    }
}
