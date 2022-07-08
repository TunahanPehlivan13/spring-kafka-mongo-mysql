package com.zapu.property.read.controller;

import com.zapu.property.read.controller.response.PropertyResponse;
import com.zapu.property.read.converter.Converter;
import com.zapu.property.read.model.Property;
import com.zapu.property.read.service.PropertyReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PropertyReadController {

    private final PropertyReadService propertyReadService;
    private final Converter<Property, PropertyResponse> propertyConverter;

    @GetMapping("/detay/{propertyId}")
    public ResponseEntity<PropertyResponse> findById(@PathVariable String propertyId) {
        Optional<Property> mayProperty = propertyReadService.findById(propertyId);
        if (mayProperty.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PropertyResponse propertyResponse = propertyConverter.convert(mayProperty.get());
        return ResponseEntity.ok(propertyResponse);
    }
}
