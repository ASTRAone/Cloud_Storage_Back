package com.cloudstorage.common.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ShareStatus {
    SHARED("расшарена"),
    PRIVATE("приватна");
    private final String description;
}

