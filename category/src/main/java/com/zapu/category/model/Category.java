package com.zapu.category.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("category")
@Getter
@Setter
public class Category {

    @Id
    private String id;

    private Long categoryId;

    private String name;
}
