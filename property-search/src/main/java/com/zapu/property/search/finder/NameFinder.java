package com.zapu.property.search.finder;

import org.springframework.stereotype.Component;

@Component
public class NameFinder<T extends UniqueNameSupports> {

    public T find(String name, Iterable<T> items) {
        for (T item : items) {
            UniqueNameSupports current = item;
            if (name.equalsIgnoreCase(current.getName())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unable to find id with name=" + name);
    }
}
