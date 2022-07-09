package com.zapu.property.search.client.dto;

import com.zapu.property.search.finder.UniqueIDSupports;
import com.zapu.property.search.finder.UniqueNameSupports;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CategoryDto implements Serializable, UniqueIDSupports, UniqueNameSupports {
    private static final long serialVersionUID = -5513552468399678693L;

    private Long id;
    private String name;
}
