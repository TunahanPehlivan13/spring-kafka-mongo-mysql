package com.zapu.property.store.producer;

import com.zapu.property.store.constant.TopicNames;
import com.zapu.property.store.model.event.NewPropertyAddedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewPropertyAddedEventProducer {

    private final KafkaTemplate<String, NewPropertyAddedEvent> newPropertyAddedEventKafkaTemplate;

    public void produce(NewPropertyAddedEvent newPropertyAddedEvent) {
        ListenableFuture<SendResult<String, NewPropertyAddedEvent>> result =
                newPropertyAddedEventKafkaTemplate.send(TopicNames.NEW_PROPERTY_ADDED_NAME, newPropertyAddedEvent);

        result.addCallback(successCallback(newPropertyAddedEvent), failureCallback(newPropertyAddedEvent));
    }

    private FailureCallback failureCallback(NewPropertyAddedEvent event) {
        return onFailure -> log.error("Failed to produce event type of {} with data={}",
                TopicNames.NEW_PROPERTY_ADDED_NAME, event);
    }

    private SuccessCallback<SendResult<String, NewPropertyAddedEvent>> successCallback(NewPropertyAddedEvent event) {
        return onSuccess -> log.info("Successfully produced event type of '{}' with data={}",
                TopicNames.NEW_PROPERTY_ADDED_NAME, event);
    }
}
