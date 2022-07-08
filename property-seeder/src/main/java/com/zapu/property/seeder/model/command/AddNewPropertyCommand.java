package com.zapu.property.seeder.model.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class AddNewPropertyCommand implements Serializable {
    private static final long serialVersionUID = 8069489113202413681L;

    private Long categoryId;
    private Long cityId;
    private String title;
    private String currency;
    private BigDecimal price;
}
