package com.cloudstorage.common.processing.enumeration;

import com.cloudstorage.common.processing.api.ProcessingResultStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommonProcessingResultStatus implements ProcessingResultStatus {
    FAILED("Ошибка", true),
    SUCCESS("Успешно", false);
    private final String description;
    private final boolean failed;
}
