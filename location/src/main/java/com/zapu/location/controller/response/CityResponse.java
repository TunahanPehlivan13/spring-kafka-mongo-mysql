package com.zapu.location.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CityResponse implements Serializable {
    private static final long serialVersionUID = 5316480353425028179L;

    private Long id;
    private String name;
}
