package com.zapu.location.service;

import com.zapu.location.model.City;
import com.zapu.location.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }
}
