package com.zapu.property.viewer.converter;

public interface Converter<S, D> {

    D convert(S source);
}