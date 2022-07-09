package com.zapu.property.search.controller.request;

import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Setter
public class SearchRequest implements Serializable {
    private static final long serialVersionUID = 4563609670823116716L;

    @Min(value = 1)
    private Integer page;

    private List<Long> city;

    @Min(value = 1)
    @NotNull
    private Long category;

    public Optional<Integer> getPage() {
        return Optional.ofNullable(page);
    }

    public Long getCategoryId() {
        return category;
    }

    public Optional<List<Long>> getCityIds() {
        return Optional.ofNullable(city);
    }
}
