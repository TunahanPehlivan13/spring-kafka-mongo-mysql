package com.zapu.property.viewer.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class PropertyResponse implements Serializable {
    private static final long serialVersionUID = -7474067710206286652L;

    private Long categoryId;
    private Long cityId;
    private String title;
    private String currency;
    private BigDecimal price;
}
