package com.zapu.property.store.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Document("property")
public class PropertyDocument implements Serializable {
    private static final long serialVersionUID = -530733218912989108L;

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
