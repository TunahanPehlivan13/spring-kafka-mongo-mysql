package com.zapu.property.read.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("property")
@Getter
@Setter
public class Property {

    @Id
    private String id;

    @Indexed
    private Long categoryId;

    @Indexed
    private Long cityId;

    private String title;

    private String currency;

    private BigDecimal price;
}
