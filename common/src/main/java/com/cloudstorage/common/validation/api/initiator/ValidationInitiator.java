package com.cloudstorage.common.validation.api.initiator;


import com.cloudstorage.common.validation.api.enumeration.Action;
import com.cloudstorage.common.validation.model.ValidationResult;

public interface ValidationInitiator<T> {
    ValidationResult initValidations(T validationObject, Action action);
}
