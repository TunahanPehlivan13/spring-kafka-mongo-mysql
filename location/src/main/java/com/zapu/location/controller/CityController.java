package com.zapu.location.controller;

import com.zapu.location.controller.response.CityResponse;
import com.zapu.location.converter.Converter;
import com.zapu.location.model.City;
import com.zapu.location.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private final Converter<City, CityResponse> cityConverter;

    @GetMapping("/api/cities")
    public Iterable<CityResponse> findAll() {
        Iterable<City> cities = cityService.findAll();
        return cityConverter.convert(cities);
    }
}
