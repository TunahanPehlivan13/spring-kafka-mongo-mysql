package com.zapu.property.store.consumer;

import com.zapu.property.store.constant.TopicNames;
import com.zapu.property.store.executor.PropertyStoreFlowExecutor;
import com.zapu.property.store.model.command.AddNewPropertyCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddNewPropertyCommandConsumer {

    private final PropertyStoreFlowExecutor propertyStoreFlowExecutor;

    @KafkaListener(topics =
            TopicNames.ADD_NEW_PROPERTY_COMMAND_NAME,
            containerFactory = "addNewPropertyCommandKafkaListenerFactory")
    public void processAddNewPropertyCommand(AddNewPropertyCommand addNewPropertyCommand) {
        log.info("Successfully consumed event with data={}", addNewPropertyCommand);
        propertyStoreFlowExecutor.execute(addNewPropertyCommand);
    }
}
