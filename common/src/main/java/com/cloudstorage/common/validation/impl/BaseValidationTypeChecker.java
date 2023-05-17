package com.cloudstorage.common.validation.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import com.cloudstorage.common.validation.api.ValidationType;
import com.cloudstorage.common.validation.api.ValidationTypeChecker;

import java.util.Collection;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseValidationTypeChecker implements ValidationTypeChecker {
    protected final Collection<ValidationType> supportedValidationTypes;

    @Override
    public boolean checkValidationType(ValidationType validationType) {
        return supportedValidationTypes.contains(validationType);
    }
}
