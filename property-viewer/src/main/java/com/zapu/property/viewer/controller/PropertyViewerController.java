package com.zapu.property.viewer.controller;

import com.zapu.property.viewer.controller.response.PropertyResponse;
import com.zapu.property.viewer.converter.Converter;
import com.zapu.property.viewer.model.PropertyDocument;
import com.zapu.property.viewer.service.PropertyViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PropertyViewerController {

    private final PropertyViewerService propertyViewerService;
    private final Converter<PropertyDocument, PropertyResponse> propertyConverter;

    @GetMapping("/detay/{propertyId}")
    public ResponseEntity<PropertyResponse> findById(@PathVariable String propertyId) {
        Optional<PropertyDocument> mayProperty = propertyViewerService.findById(propertyId);
        if (mayProperty.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PropertyResponse propertyResponse = propertyConverter.convert(mayProperty.get());
        return ResponseEntity.ok(propertyResponse);
    }
}
