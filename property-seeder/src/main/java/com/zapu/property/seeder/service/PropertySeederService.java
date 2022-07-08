package com.zapu.property.seeder.service;

import com.zapu.property.seeder.controller.request.PropertyRequest;
import com.zapu.property.seeder.producer.AddNewPropertyCommandSeeder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertySeederService {

    private final AddNewPropertyCommandSeeder addNewPropertyCommandSeeder;

    public void addNewProperty(PropertyRequest propertyRequest) {
        addNewPropertyCommandSeeder.seed(propertyRequest);
    }
}
