package com.zapu.property.search.repository;

import com.zapu.property.search.model.PropertyDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PropertyRepository extends PagingAndSortingRepository<PropertyDocument, String> {

    Page<PropertyDocument> findByCategoryId(Long categoryId, Pageable pageable);

    Page<PropertyDocument> findByCategoryIdAndCityId(Long categoryId, Long cityId, Pageable pageable);

    Page<PropertyDocument> findByCategoryIdAndCityIdIn(Long categoryId, List<Long> cityId, Pageable pageable);
}
