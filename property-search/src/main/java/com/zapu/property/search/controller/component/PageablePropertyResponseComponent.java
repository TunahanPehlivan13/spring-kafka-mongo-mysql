package com.zapu.property.search.controller.component;

import com.zapu.property.search.controller.response.PageablePropertyResponse;
import com.zapu.property.search.controller.response.PropertyResponse;
import com.zapu.property.search.converter.Converter;
import com.zapu.property.search.model.PropertyDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageablePropertyResponseComponent {

    private final Converter<PropertyDocument, PropertyResponse> propertyConverter;

    public PageablePropertyResponse createResponse(Page<PropertyDocument> result, String uri) {
        return PageablePropertyResponse.builder()
                .page(result.getNumber() + 1)
                .pageSize(result.getSize())
                .totalPage(result.getTotalPages())
                .result(propertyConverter.convert(result.getContent()))
                .rootUrl(uri)
                .build();
    }
}
