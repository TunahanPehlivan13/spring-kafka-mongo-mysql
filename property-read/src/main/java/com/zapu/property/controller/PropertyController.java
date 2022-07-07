package com.zapu.property.controller;

import com.zapu.property.controller.response.PropertyResponse;
import com.zapu.property.converter.Converter;
import com.zapu.property.model.Property;
import com.zapu.property.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final Converter<Property, PropertyResponse> propertyConverter;

    @GetMapping("/detay/{propertyId}")
    public ResponseEntity<PropertyResponse> findById(@PathVariable String propertyId) {
        Optional<Property> mayProperty = propertyService.findById(propertyId);
        if (mayProperty.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PropertyResponse propertyResponse = propertyConverter.convert(mayProperty.get());
        return ResponseEntity.ok(propertyResponse);
    }
}
