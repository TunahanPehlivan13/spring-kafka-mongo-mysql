package com.zapu.property.store.handler;

import com.zapu.property.store.model.PropertyDocument;

public interface PropertyStoreFlowHandler {

    void handle(PropertyDocument propertyDocument);

    void doFailover(PropertyDocument property);
}
