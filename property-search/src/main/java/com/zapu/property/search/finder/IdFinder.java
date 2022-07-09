package com.zapu.property.search.finder;

import org.springframework.stereotype.Component;

@Component
public class IdFinder<T extends UniqueIDSupports> {

    public T find(Long id, Iterable<T> items) {
        for (T item : items) {
            UniqueIDSupports current = item;
            if (id.equals(current.getId())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unable to find name with id=" + id);
    }
}
