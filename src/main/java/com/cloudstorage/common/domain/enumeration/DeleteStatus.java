package com.cloudstorage.common.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DeleteStatus {
    DELETED("удален"),
    NOT_DELETED("не удален");
    private final String description;
}

