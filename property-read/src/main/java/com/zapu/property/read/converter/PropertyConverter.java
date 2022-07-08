package com.zapu.property.read.converter;

import com.zapu.property.read.controller.response.PropertyResponse;
import com.zapu.property.read.model.Property;
import org.springframework.stereotype.Component;

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

}
