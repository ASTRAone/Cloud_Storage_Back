package com.cloudstorage.common.validation.api.context;

import java.util.Optional;

public interface ValidationContext {
    <T> Optional<T> tryFind(String key);

    void put(String key, Object data);
}
