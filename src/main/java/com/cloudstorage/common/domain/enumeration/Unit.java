package com.cloudstorage.common.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * единицы измерения
 */
@RequiredArgsConstructor
@Getter
public enum Unit {
    MEGABYTE("мегабайт"),
    KILOBYTE("килобайт"),
    GIGABYTE("гигабайт");
    private final String description;
}

