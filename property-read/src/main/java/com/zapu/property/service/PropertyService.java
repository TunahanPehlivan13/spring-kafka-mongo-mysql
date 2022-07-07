package com.zapu.property.service;

import com.zapu.property.model.Property;
import com.zapu.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public Optional<Property> findById(String propertyId) {
        return propertyRepository.findById(propertyId);
    }
}
