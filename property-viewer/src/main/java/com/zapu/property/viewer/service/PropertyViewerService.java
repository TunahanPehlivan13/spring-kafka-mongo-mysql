package com.zapu.property.viewer.service;

import com.zapu.property.viewer.model.Property;
import com.zapu.property.viewer.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyViewerService {

    private final PropertyRepository propertyRepository;

    public Optional<Property> findById(String propertyId) {
        return propertyRepository.findById(propertyId);
    }
}
