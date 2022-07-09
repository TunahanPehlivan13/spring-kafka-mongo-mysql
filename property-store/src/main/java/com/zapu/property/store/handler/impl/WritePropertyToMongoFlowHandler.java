package com.zapu.property.store.handler.impl;

import com.zapu.property.store.handler.PropertyStoreFlowHandler;
import com.zapu.property.store.model.PropertyDocument;
import com.zapu.property.store.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WritePropertyToMongoFlowHandler implements PropertyStoreFlowHandler {

    private final PropertyRepository propertyRepository;

    @Override
    public void handle(PropertyDocument propertyDocument) {
        propertyRepository.save(propertyDocument);
    }

    @Override
    public void doFailover(PropertyDocument property) {
        // TODO consider a DLQ implementation after removed the property from mongo if it existed
    }
}
