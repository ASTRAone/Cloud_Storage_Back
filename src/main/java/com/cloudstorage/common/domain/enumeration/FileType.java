package com.cloudstorage.common.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FileType {
    AUDIO("аудио"),
    PICTURE("картинка"),
    DOCUMENT("документ"),
    VIDEO("видео"),
    OTHER("другое");
    private final String description;
}