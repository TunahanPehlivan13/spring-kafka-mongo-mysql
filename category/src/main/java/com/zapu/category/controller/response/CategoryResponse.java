package com.zapu.category.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CategoryResponse implements Serializable {
    private static final long serialVersionUID = 6105162256427501361L;

    private Long id;
    private String name;
}
