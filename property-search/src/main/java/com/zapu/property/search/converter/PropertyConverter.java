package com.zapu.property.search.converter;

import com.zapu.property.search.client.CategoryClientAdapter;
import com.zapu.property.search.client.LocationClientAdapter;
import com.zapu.property.search.client.dto.CategoryDto;
import com.zapu.property.search.client.dto.CityDto;
import com.zapu.property.search.controller.response.PropertyResponse;
import com.zapu.property.search.finder.IdFinder;
import com.zapu.property.search.model.PropertyDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class PropertyConverter implements Converter<PropertyDocument, PropertyResponse> {

    private final IdFinder<CategoryDto> categoryIdFinder;
    private final IdFinder<CityDto> cityIdFinder;
    private final LocationClientAdapter locationClientAdapter;
    private final CategoryClientAdapter categoryClientAdapter;

    @Override
    public PropertyResponse convert(PropertyDocument source) {
        Iterable<CategoryDto> categories = categoryClientAdapter.findAll();
        Iterable<CityDto> cities = locationClientAdapter.findAll();

        PropertyResponse propertyResponse = new PropertyResponse();
        propertyResponse.setId(source.getId());

        CategoryDto category = categoryIdFinder.find(source.getCategoryId(), categories);
        propertyResponse.setCategory(category.getName());

        CityDto city = cityIdFinder.find(source.getCityId(), cities);
        propertyResponse.setCity(city.getName());

        propertyResponse.setCurrency(source.getCurrency());
        propertyResponse.setPrice(source.getPrice());
        propertyResponse.setTitle(source.getTitle());
        return propertyResponse;
    }

    @Override
    public Iterable<PropertyResponse> convert(Iterable<PropertyDocument> sources) {
        return StreamSupport.stream(sources.spliterator(), false)
                .map(this::convert)
                .collect(Collectors.toUnmodifiableSet());
    }

}
