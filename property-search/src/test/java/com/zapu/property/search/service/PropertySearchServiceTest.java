package com.zapu.property.search.service;

import com.zapu.property.search.client.CategoryClientAdapter;
import com.zapu.property.search.client.LocationClientAdapter;
import com.zapu.property.search.client.dto.CategoryDto;
import com.zapu.property.search.client.dto.CityDto;
import com.zapu.property.search.finder.NameFinder;
import com.zapu.property.search.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertySearchServiceTest {

    @InjectMocks
    private PropertySearchService propertySearchService;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private CategoryClientAdapter categoryClientAdapter;

    @Mock
    private LocationClientAdapter locationClientAdapter;

    @Spy
    private NameFinder<CategoryDto> categoryNameFinder;

    @Spy
    private NameFinder<CityDto> cityNameFinder;

    @Test
    void shouldGetPropertiesByCategory() {
        // given
        CategoryDto category = new CategoryDto();
        category.setName("category");
        category.setId(1L);

        Iterable<CategoryDto> categories = Set.of(category);

        // when
        when(categoryClientAdapter.getAll()).thenReturn(categories);

        // then
        propertySearchService.search("category", Optional.empty(), 1);

        verify(propertyRepository, times(1))
                .findByCategoryId(1L, PageRequest.of(0, 5));
    }

    @Test
    void shouldGetPropertiesByCategoryAndCity() {
        // given
        CategoryDto category = new CategoryDto();
        category.setName("category");
        category.setId(1L);

        CityDto city = new CityDto();
        city.setName("city");
        city.setId(1L);

        Iterable<CategoryDto> categories = Set.of(category);
        Iterable<CityDto> cities = Set.of(city);

        // when
        when(categoryClientAdapter.getAll()).thenReturn(categories);
        when(locationClientAdapter.getAllCities()).thenReturn(cities);

        // then
        propertySearchService.search("category", Optional.of("city"), 1);

        verify(propertyRepository, times(1))
                .findByCategoryIdAndCityId(1L, 1L, PageRequest.of(0, 5));
    }

    @Test
    void shouldGetPropertiesByCategoryAndAListOfCity() {
        propertySearchService.search(1L, List.of(1L, 2L), 1);

        verify(propertyRepository, times(1))
                .findByCategoryIdAndCityIdIn(1L, List.of(1L, 2L), PageRequest.of(0, 5));
    }
}