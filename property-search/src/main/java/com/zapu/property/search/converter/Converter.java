package com.zapu.property.search.converter;

public interface Converter<S, D> {

    D convert(S source);

    Iterable<D> convert(Iterable<S> source);
}