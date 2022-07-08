package com.zapu.property.read.service;

import com.zapu.property.read.model.Property;
import com.zapu.property.read.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyReadService {

    private final PropertyRepository propertyRepository;

    public Optional<Property> findById(String propertyId) {
        return propertyRepository.findById(propertyId);
    }
}
