package com.cloudstorage.common.validation.api.rule;


import com.cloudstorage.common.validation.api.ValidationTypeChecker;
import com.cloudstorage.common.validation.api.context.ValidationContext;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;

public interface ValidationRule<T> extends ValidationTypeChecker {
    ValidationPartResult validate(T validationObject, ValidationContext context);
}
