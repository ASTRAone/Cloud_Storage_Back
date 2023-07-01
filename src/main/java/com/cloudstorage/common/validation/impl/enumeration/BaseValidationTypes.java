package com.cloudstorage.common.validation.impl.enumeration;

import com.cloudstorage.common.validation.api.ValidationType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BaseValidationTypes implements ValidationType {
    NONE("Не валидировать");
    private final String description;
}
