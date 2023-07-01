package com.cloudstorage.common.validation.model;

import com.cloudstorage.common.validation.api.enumeration.ValidationStatus;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;

import java.util.Collection;

public record SimpleValidationResult(ValidationStatus status,
                                     Collection<ValidationPartResult> validationPartResults)
        implements ValidationResult {


    public static ValidationResult success() {
        return new SimpleValidationResult(ValidationStatus.SUCCESS, null);
    }
}
