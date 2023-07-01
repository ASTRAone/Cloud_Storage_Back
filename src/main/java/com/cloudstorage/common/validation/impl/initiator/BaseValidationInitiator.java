package com.cloudstorage.common.validation.impl.initiator;

import com.cloudstorage.common.utils.CommonCollections;
import com.cloudstorage.common.validation.api.ValidationType;
import com.cloudstorage.common.validation.api.enumeration.Action;
import com.cloudstorage.common.validation.api.enumeration.ValidationStatus;
import com.cloudstorage.common.validation.api.initiator.ValidationInitiator;
import com.cloudstorage.common.validation.api.processor.ValidationProcessor;
import com.cloudstorage.common.validation.impl.enumeration.BaseValidationTypes;
import com.cloudstorage.common.validation.model.SimpleValidationResult;
import com.cloudstorage.common.validation.model.ValidationResult;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public abstract class BaseValidationInitiator<T, P extends ValidationProcessor<T>>
        implements ValidationInitiator<T> {

    private final List<P> validationProcessors;

    private ValidationStatus computeValidationStatus(Collection<ValidationPartResult> validationPartResults) {
        return CommonCollections.stream(validationPartResults)
                .map(ValidationPartResult::status)
                .filter(ValidationStatus::isFailed)
                .findFirst()
                .orElse(ValidationStatus.SUCCESS);
    }

    @Override
    public ValidationResult initValidations(T validationObject, Action action) {
        if (isNull(validationObject)) {
            throw new IllegalArgumentException("validationObject is null");
        }

        ValidationType validationType = resolveValidationType(action);
        if (BaseValidationTypes.NONE.equals(validationType)) {
            return SimpleValidationResult.success();
        }

        Collection<ValidationPartResult> validationPartResults = new HashSet<>();
        for (ValidationProcessor<T> validationProcessor : validationProcessors) {
            if (validationProcessor.checkValidationType(validationType)) {
                validationPartResults.addAll(validationProcessor.validate(validationObject, validationType));
            }
        }
        return new SimpleValidationResult(computeValidationStatus(validationPartResults), validationPartResults);
    }

    protected abstract ValidationType resolveValidationType(Action action);
}
