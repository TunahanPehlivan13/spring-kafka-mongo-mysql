package com.zapu.property.store.converter;

public interface Converter<S, D> {

    D convert(S source);
}
