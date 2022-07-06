package com.zapu.location.converter;

public interface Converter<S, D> {

    D convert(S source);

    Iterable<D> convert(Iterable<S> sources);
}
