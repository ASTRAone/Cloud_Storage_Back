package com.cloudstorage.common.processing.impl;

import com.cloudstorage.common.processing.api.ProcessingResult;
import com.cloudstorage.common.processing.api.ProcessingResultStatus;
import com.cloudstorage.common.processing.enumeration.CommonProcessingResultStatus;
import com.cloudstorage.common.validation.model.part.ValidationPartResult;

import java.util.Collection;

public record SimpleImmutableProcessingResult<T>(T content,
                                                 ProcessingResultStatus status,
                                                 Collection<ValidationPartResult> errors)
        implements ProcessingResult<T> {

    public static <DTO> ProcessingResult<DTO> ofSuccess(DTO dto) {
        return new SimpleImmutableProcessingResult<>(dto,
                CommonProcessingResultStatus.SUCCESS, null);
    }

    public static <DTO> ProcessingResult<DTO> ofFailed(Collection<ValidationPartResult> errors) {
        return new SimpleImmutableProcessingResult<>(null,
                CommonProcessingResultStatus.FAILED, errors);
    }
}
