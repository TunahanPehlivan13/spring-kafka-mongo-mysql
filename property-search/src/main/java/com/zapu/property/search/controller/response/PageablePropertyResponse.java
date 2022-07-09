package com.zapu.property.search.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PageablePropertyResponse implements Serializable {
    private static final long serialVersionUID = 553683704439629747L;

    private Integer page;
    private Integer pageSize;
    private Integer totalPage;
    private String rootUrl;

    private Iterable<PropertyResponse> result;
}
