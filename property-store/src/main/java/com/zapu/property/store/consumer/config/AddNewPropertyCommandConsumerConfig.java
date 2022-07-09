package com.zapu.property.store.consumer.config;

import com.zapu.property.store.model.command.AddNewPropertyCommand;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AddNewPropertyCommandConsumerConfig {

    @Value("${CONCURRENCY:1}")
    private Integer NUMBER_OF_CONCURRENCY;

    @Value("${KAFKA_BOOTSTRAP_SERVER:localhost:9092}")
    private String BOOTSTRAP_SERVER;

    @Value("${GROUP_ID:property-store-local-group}")
    private String GROUP_ID;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AddNewPropertyCommand> addNewPropertyCommandKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AddNewPropertyCommand> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(NUMBER_OF_CONCURRENCY);
        factory.setConsumerFactory(addNewPropertyCommandConsumerFactory());
        return factory;
    }

    private ConsumerFactory<String, AddNewPropertyCommand> addNewPropertyCommandConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config,
                new StringDeserializer(),
                new JsonDeserializer<>(AddNewPropertyCommand.class));
    }

}
