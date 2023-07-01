package com.cloudstorage.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.stream.Stream;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonCollections {
    public static <T> Stream<T> stream(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return Stream.empty();
        }

        return collection.stream();
    }
}
