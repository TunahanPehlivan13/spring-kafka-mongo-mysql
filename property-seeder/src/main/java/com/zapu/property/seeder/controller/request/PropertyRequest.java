package com.zapu.property.seeder.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class PropertyRequest implements Serializable {
    private static final long serialVersionUID = 6567950978584175236L;

    private Long categoryId;
    private Long cityId;
    private String title;
    private String currency;
    private BigDecimal price;
}
