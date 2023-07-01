package com.cloudstorage.common.validation.impl.rule;


import com.cloudstorage.common.validation.api.ValidationErrorType;
import com.cloudstorage.common.validation.api.ValidationType;
import com.cloudstorage.common.validation.api.enumeration.ValidationStatus;
import com.cloudstorage.common.validation.api.rule.ValidationRule;
import com.cloudstorage.common.validation.impl.BaseValidationTypeChecker;
import com.cloudstorage.common.validation.model.part.SimpleValidationPartResult;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;

import java.util.List;


public abstract class BaseValidationRule<T>
        extends BaseValidationTypeChecker
        implements ValidationRule<T> {

    private final ValidationErrorType errorType;

    protected BaseValidationRule(List<ValidationType> validationTypes,
                                 ValidationErrorType errorType) {
        super(validationTypes);
        this.errorType = errorType;
    }

    protected ValidationPartResult successValidationResult() {
        return new SimpleValidationPartResult(null, ValidationStatus.SUCCESS, errorType.fieldName());
    }

    protected ValidationPartResult failedValidationResult() {
        return new SimpleValidationPartResult(errorType, ValidationStatus.FAILED, errorType.fieldName());
    }
}
