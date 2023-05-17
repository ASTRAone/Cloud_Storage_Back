package com.cloudstorage.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.stream.Stream;

import static org.springframework.util.CollectionUtils.isEmpty;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonCollections {
    public static <T> Stream<T> stream(Collection<T> collection) {
        if (isEmpty(collection)) {
            return Stream.empty();
        }

        return collection.stream();
    }
}
