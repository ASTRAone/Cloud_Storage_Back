package com.cloudstorage.common.validation.impl.validator;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import com.cloudstorage.common.validation.api.ValidationType;
import com.cloudstorage.common.validation.api.context.ValidationContext;
import com.cloudstorage.common.validation.api.rule.ValidationRule;
import com.cloudstorage.common.validation.api.validator.Validator;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseValidator<T> implements Validator<T> {
    protected final List<ValidationRule<T>> validationRules;

    @Override
    public Collection<ValidationPartResult> validate(T validationObject,
                                                     ValidationType validationType,
                                                     ValidationContext context) {
        return validationRules
                .stream()
                .filter(validationRule -> validationRule.checkValidationType(validationType))
                .map(validationRule -> validationRule.validate(validationObject, context))
                .filter(Objects::nonNull)
                .filter(result -> result.status().isFailed())
                .toList();
    }
}
