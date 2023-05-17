package com.cloudstorage.common.validation.api.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ValidationStatus {
    SUCCESS("Валидация пройдена", false),
    FAILED("Валидация провалена", true);

    private final String description;
    private final boolean failed;
}
