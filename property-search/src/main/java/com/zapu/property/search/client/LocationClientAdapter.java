package com.zapu.property.search.client;

import com.zapu.property.search.client.dto.CityDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "locationClient", url = "${location.service.url}")
public interface LocationClientAdapter {

    @Cacheable(value = "cities")
    @RequestMapping(method = RequestMethod.GET, value = "/api/cities")
    Iterable<CityDto> findAll();
}
