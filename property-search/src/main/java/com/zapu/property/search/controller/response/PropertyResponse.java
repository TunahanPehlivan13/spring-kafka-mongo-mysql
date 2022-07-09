package com.zapu.property.search.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PropertyResponse {

    private String id;
    private String category;
    private String city;
    private String title;
    private String currency;
    private BigDecimal price;
}
