package com.zapu.property.store.converter;

import com.zapu.property.store.model.PropertyDocument;
import com.zapu.property.store.model.event.NewPropertyAddedEvent;
import org.springframework.stereotype.Component;

@Component
public class NewPropertyAddedEventConverter implements Converter<PropertyDocument, NewPropertyAddedEvent> {

    @Override
    public NewPropertyAddedEvent convert(PropertyDocument source) {
        NewPropertyAddedEvent newPropertyAddedEvent = new NewPropertyAddedEvent();
        newPropertyAddedEvent.setId(source.getId());
        return newPropertyAddedEvent;
    }
}
