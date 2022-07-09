package com.zapu.property.search.controller;

import com.zapu.property.search.controller.component.FriendlyURLComponent;
import com.zapu.property.search.controller.component.PageablePropertyResponseComponent;
import com.zapu.property.search.controller.request.SearchRequest;
import com.zapu.property.search.controller.response.PageablePropertyResponse;
import com.zapu.property.search.model.PropertyDocument;
import com.zapu.property.search.service.PropertySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PropertySearchController {

    private static final String LOCATION_HEADER_NAME = "Location";

    private final PropertySearchService propertySearchService;
    private final PageablePropertyResponseComponent pageablePropertyResponseComponent;
    private final FriendlyURLComponent friendlyURLComponent;

    @GetMapping("/arama")
    public ResponseEntity search(@ModelAttribute @Valid SearchRequest searchRequest) {
        if (hasLessThan2Cities(searchRequest.getCityIds())) {
            return redirectToFriendlyUrl(searchRequest);
        }
        List<Long> cityIds = searchRequest.getCityIds().get();
        Page<PropertyDocument> result = propertySearchService.search(
                searchRequest.getCategoryId(), cityIds, searchRequest.getPage().orElse(1));

        PageablePropertyResponse response = pageablePropertyResponseComponent.createResponse(
                result, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.ok(response);
    }

    private boolean hasLessThan2Cities(Optional<List<Long>> mayCity) {
        return mayCity.isEmpty() || mayCity.get().size() < 2;
    }

    private ResponseEntity redirectToFriendlyUrl(SearchRequest searchRequest) {
        StringBuilder redirectUrl = new StringBuilder("/arama");

        String categoryName = friendlyURLComponent.findCategoryById(searchRequest.getCategoryId()).getName();
        redirectUrl.append("/").append(categoryName.toLowerCase());

        Optional<List<Long>> mayCity = searchRequest.getCityIds();
        boolean isCityPresent = mayCity.isPresent() && mayCity.get().size() > 0;
        if (isCityPresent) {
            Long cityId = mayCity.get().get(0);
            String cityName = friendlyURLComponent.findCityById(cityId).getName();
            redirectUrl.append("/").append(cityName.toLowerCase());
        }

        Optional<Integer> mayPage = searchRequest.getPage();
        if (mayPage.isPresent()) {
            Integer page = mayPage.get();
            redirectUrl.append("?page=").append(page);
        }
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header(LOCATION_HEADER_NAME, redirectUrl.toString())
                .build();
    }

    @GetMapping("/arama/{category}")
    public ResponseEntity<PageablePropertyResponse> search(
            @PathVariable("category") String categoryId, @RequestParam(defaultValue = "1") Integer page) {

        Page<PropertyDocument> result = propertySearchService.search(categoryId, Optional.empty(), page);
        PageablePropertyResponse response = pageablePropertyResponseComponent.createResponse(
                result, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/arama/{category}/{city}")
    public ResponseEntity<PageablePropertyResponse> search(
            @PathVariable("category") String categoryId, @PathVariable("city") String cityId, @RequestParam(defaultValue = "1") Integer page) {

        Page<PropertyDocument> result = propertySearchService.search(categoryId, Optional.of(cityId), page);
        PageablePropertyResponse response = pageablePropertyResponseComponent.createResponse(
                result, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.ok(response);
    }
}
