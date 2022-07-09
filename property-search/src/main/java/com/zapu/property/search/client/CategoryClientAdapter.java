package com.zapu.property.search.client;

import com.zapu.property.search.client.dto.CategoryDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "categoryClient", url = "${category.service.url}")
public interface CategoryClientAdapter {

    @Cacheable(value = "categories")
    @RequestMapping(method = RequestMethod.GET, value = "/api/categories")
    Iterable<CategoryDto> findAll();
}
