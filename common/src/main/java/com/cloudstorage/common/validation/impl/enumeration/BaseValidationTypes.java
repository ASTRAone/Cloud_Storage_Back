package com.cloudstorage.common.validation.impl.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.cloudstorage.common.validation.api.ValidationType;

@RequiredArgsConstructor
@Getter
public enum BaseValidationTypes implements ValidationType {
    NONE("Не валидировать");
    private final String description;
}
