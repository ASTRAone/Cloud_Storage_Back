package com.cloudstorage.common.validation.api.enumeration;

import com.cloudstorage.common.validation.api.ValidationErrorType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserErrorType implements ValidationErrorType {
    NICKNAME("nickname"),
    NICKNAME_ALREADY_EXISTS("nickname"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    FIO("fio");
    private final String fieldName;

    @Override
    public String fieldName() {
        return fieldName;
    }
}
