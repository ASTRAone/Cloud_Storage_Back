package com.cloudstorage.common.validation.model;

import com.cloudstorage.common.validation.api.enumeration.ValidationStatus;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;

import java.util.Collection;

public interface ValidationResult {
    Collection<ValidationPartResult> validationPartResults();

    ValidationStatus status();
}
