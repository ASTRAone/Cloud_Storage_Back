package com.cloudstorage.filestorage.converter.api;

public interface Converter<T, R> {
    R convert(T input);
}
