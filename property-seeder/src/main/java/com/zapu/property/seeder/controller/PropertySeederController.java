package com.zapu.property.seeder.controller;

import com.zapu.property.seeder.controller.request.PropertyRequest;
import com.zapu.property.seeder.service.PropertySeederService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PropertySeederController {

    private final PropertySeederService propertySeederService;

    @PostMapping("/api/property")
    public ResponseEntity addNewProperty(@RequestBody @Valid PropertyRequest propertyRequest) {
        propertySeederService.addNewProperty(propertyRequest);
        return ResponseEntity.ok().build();
    }
}
