package com.zapu.property.search.client.dto;

import com.zapu.property.search.finder.UniqueIDSupports;
import com.zapu.property.search.finder.UniqueNameSupports;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CityDto implements Serializable, UniqueIDSupports, UniqueNameSupports {
    private static final long serialVersionUID = 6310866353985737934L;

    private Long id;
    private String name;
}
