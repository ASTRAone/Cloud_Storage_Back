package com.cloudstorage.common.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ShareScope {
    LINK("по ссылке для любого пользователя"),
    USER("для конкретных пользователей");
    private final String description;
}
