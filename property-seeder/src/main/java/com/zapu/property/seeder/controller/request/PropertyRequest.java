package com.zapu.property.seeder.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class PropertyRequest implements Serializable {
    private static final long serialVersionUID = 6567950978584175236L;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long cityId;

    @NotNull
    private String title;

    @NotNull
    private String currency;

    @NotNull
    private BigDecimal price;
}
