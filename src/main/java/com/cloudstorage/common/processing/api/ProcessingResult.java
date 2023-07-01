package com.cloudstorage.common.processing.api;

import com.cloudstorage.common.validation.model.part.ValidationPartResult;

import java.util.Collection;

public interface ProcessingResult<T> {
    ProcessingResultStatus status();

    T content();

    Collection<ValidationPartResult> errors();
}
