package com.zapu.property.converter;

import com.zapu.property.controller.response.PropertyResponse;
import com.zapu.property.model.Property;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PropertyConverter implements Converter<Property, PropertyResponse> {

    @Override
    public PropertyResponse convert(Property source) {
        PropertyResponse propertyResponse = new PropertyResponse();
        propertyResponse.setCategoryId(source.getCategoryId());
        propertyResponse.setCityId(source.getCityId());
        propertyResponse.setCurrency(source.getCurrency());
        propertyResponse.setPrice(source.getPrice());
        propertyResponse.setTitle(source.getTitle());
        return propertyResponse;
    }

    @Override
    public Iterable<PropertyResponse> convert(Iterable<Property> sources) {
        return StreamSupport.stream(sources.spliterator(), false)
                .map(this::convert)
                .collect(Collectors.toUnmodifiableSet());
    }

}
