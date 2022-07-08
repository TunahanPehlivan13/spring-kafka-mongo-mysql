package com.zapu.property.read.converter;

public interface Converter<S, D> {

    D convert(S source);
}