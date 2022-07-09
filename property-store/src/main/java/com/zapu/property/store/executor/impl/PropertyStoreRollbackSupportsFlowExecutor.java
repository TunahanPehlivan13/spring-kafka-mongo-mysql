package com.zapu.property.store.executor.impl;

import com.zapu.property.store.converter.Converter;
import com.zapu.property.store.executor.PropertyStoreFlowExecutor;
import com.zapu.property.store.handler.PropertyStoreFlowHandler;
import com.zapu.property.store.handler.impl.ProducePropertyAddedEventFlowHandler;
import com.zapu.property.store.handler.impl.WritePropertyToMongoFlowHandler;
import com.zapu.property.store.model.PropertyDocument;
import com.zapu.property.store.model.command.AddNewPropertyCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyStoreRollbackSupportsFlowExecutor implements PropertyStoreFlowExecutor {

    private final WritePropertyToMongoFlowHandler writePropertyToMongoFlowHandler;
    private final ProducePropertyAddedEventFlowHandler producePropertyAddedEventFlowHandler;

    private final Converter<AddNewPropertyCommand, PropertyDocument> propertyConverter;

    @Override
    public void execute(AddNewPropertyCommand addNewPropertyCommand) {
        PropertyDocument property = propertyConverter.convert(addNewPropertyCommand);
        log.info("Storing property processes will be started for '{}'", addNewPropertyCommand);

        List<PropertyStoreFlowHandler> flowHandlers = getSortedFlowHandlers();
        for (PropertyStoreFlowHandler flowHandler : flowHandlers) {
            try {
                log.info("{} will be started with property={}", flowHandler.getClass().getName(), property);
                flowHandler.handle(property);
            } catch (Exception e) {
                log.warn("Failed to process flow with handler='{}', with data={}",
                        flowHandler.getClass().getName(), addNewPropertyCommand, e);
                startFailoverProcess(flowHandlers, property);
                return;
            }
        }
        log.info("Successfully completed storing property processes with property-id={}", property.getId());
    }

    private void startFailoverProcess(List<PropertyStoreFlowHandler> flowHandlers, PropertyDocument property) {
        for (PropertyStoreFlowHandler flowHandler : flowHandlers) {
            try {
                log.info("Started failover process with flow-handler={} property={}", flowHandler, property);
                flowHandler.doFailover(property);
            } catch (Exception e) {
                log.error("Failed to failover process with flow-handler={}, property={}",
                        flowHandler.getClass().getName(), property, e);
            }
        }
    }

    private List<PropertyStoreFlowHandler> getSortedFlowHandlers() {
        return List.of(writePropertyToMongoFlowHandler, producePropertyAddedEventFlowHandler);
    }
}
