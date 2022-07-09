package com.zapu.property.store.converter;

import com.zapu.property.store.model.PropertyDocument;
import com.zapu.property.store.model.command.AddNewPropertyCommand;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter implements Converter<AddNewPropertyCommand, PropertyDocument> {

    @Override
    public PropertyDocument convert(AddNewPropertyCommand source) {
        PropertyDocument propertyDocument = new PropertyDocument();
        propertyDocument.setCurrency(source.getCurrency());
        propertyDocument.setCategoryId(source.getCategoryId());
        propertyDocument.setCityId(source.getCityId());
        propertyDocument.setPrice(source.getPrice());
        propertyDocument.setTitle(source.getTitle());
        return propertyDocument;
    }
}
