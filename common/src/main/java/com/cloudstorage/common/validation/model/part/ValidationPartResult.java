package com.cloudstorage.common.validation.model.part;

import com.cloudstorage.common.validation.api.ValidationErrorType;
import com.cloudstorage.common.validation.api.enumeration.ValidationStatus;

public interface ValidationPartResult {
    ValidationErrorType errorType();

    ValidationStatus status();

    String fieldName();
}
