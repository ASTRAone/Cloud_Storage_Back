package com.cloudstorage.common.validation.impl.context;

import com.cloudstorage.common.validation.api.context.ValidationContext;

import java.util.HashMap;
import java.util.Optional;

public class HasMapValidationContext implements ValidationContext {
    private final HashMap<String, Object> map = new HashMap<>();

    @Override
    public <T> Optional<T> tryFind(String key) {
        return Optional.ofNullable((T) map.get(key));
    }

    @Override
    public void put(String key, Object data) {
        map.put(key, data);
    }
}
