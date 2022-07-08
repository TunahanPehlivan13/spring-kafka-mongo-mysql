package com.zapu.property.seeder.producer;

import com.zapu.property.seeder.constant.TopicNames;
import com.zapu.property.seeder.controller.request.PropertyRequest;
import com.zapu.property.seeder.converter.Converter;
import com.zapu.property.seeder.model.command.AddNewPropertyCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddNewPropertyCommandSeeder {

    private final KafkaTemplate<String, AddNewPropertyCommand> addNewPropertyCommandKafkaTemplate;
    private final Converter<PropertyRequest, AddNewPropertyCommand> propertyConverter;

    public void seed(PropertyRequest propertyRequest) {
        AddNewPropertyCommand command = propertyConverter.convert(propertyRequest);
        ListenableFuture<SendResult<String, AddNewPropertyCommand>> result =
                addNewPropertyCommandKafkaTemplate.send(TopicNames.ADD_NEW_PROPERTY_COMMAND_NAME, command);

        result.addCallback(successCallback(command), failureCallback(command));
    }

    private FailureCallback failureCallback(AddNewPropertyCommand command) {
        return onFailure -> log.error("Failed to produce event type of {} with data={}",
                TopicNames.ADD_NEW_PROPERTY_COMMAND_NAME, command);
    }

    private SuccessCallback<SendResult<String, AddNewPropertyCommand>> successCallback(AddNewPropertyCommand command) {
        return onSuccess -> log.info("Successfully produced event type of '{}' with data={}",
                TopicNames.ADD_NEW_PROPERTY_COMMAND_NAME, command);
    }
}
