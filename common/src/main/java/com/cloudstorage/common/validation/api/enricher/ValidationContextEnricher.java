package com.cloudstorage.common.validation.api.enricher;

import com.cloudstorage.common.validation.api.ValidationTypeChecker;
import com.cloudstorage.common.validation.api.context.ValidationContext;

public interface ValidationContextEnricher<T> extends ValidationTypeChecker {
    void enrich(ValidationContext validationContext, T validationObject);
}
