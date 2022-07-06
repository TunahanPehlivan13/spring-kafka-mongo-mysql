package com.zapu.location.converter;

import com.zapu.location.controller.response.CityResponse;
import com.zapu.location.model.City;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CityConverter implements Converter<City, CityResponse> {

    @Override
    public CityResponse convert(City source) {
        CityResponse cityResponse = new CityResponse();
        cityResponse.setId(source.getId());
        cityResponse.setName(source.getName());
        return cityResponse;
    }

    @Override
    public Iterable<CityResponse> convert(Iterable<City> sources) {
        return StreamSupport.stream(sources.spliterator(), false)
                .map(this::convert)
                .collect(Collectors.toUnmodifiableSet());
    }
}
