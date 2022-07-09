package com.zapu.property.store.handler.impl;

import com.zapu.property.store.converter.Converter;
import com.zapu.property.store.handler.PropertyStoreFlowHandler;
import com.zapu.property.store.model.PropertyDocument;
import com.zapu.property.store.model.event.NewPropertyAddedEvent;
import com.zapu.property.store.producer.NewPropertyAddedEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducePropertyAddedEventFlowHandler implements PropertyStoreFlowHandler {

    private final NewPropertyAddedEventProducer newPropertyAddedEventProducer;
    private final Converter<PropertyDocument, NewPropertyAddedEvent> newPropertyAddedEventConverter;

    @Override
    public void handle(PropertyDocument propertyDocument) {
        NewPropertyAddedEvent event = newPropertyAddedEventConverter.convert(propertyDocument);
        newPropertyAddedEventProducer.produce(event);
    }

    @Override
    public void doFailover(PropertyDocument property) {
        // TODO put an event to kafka that implies the process failed like 'NotAbleToAddedProperty'
    }
}
