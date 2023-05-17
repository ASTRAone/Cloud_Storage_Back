package com.cloudstorage.common.repository.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

import static java.util.Objects.isNull;

@Converter
public class StringUuidConverter implements AttributeConverter<String, UUID> {
    @Override
    public UUID convertToDatabaseColumn(String attribute) {
        return isNull(attribute) ? null : UUID.fromString(attribute);
    }

    @Override
    public String convertToEntityAttribute(UUID dbData) {
        return isNull(dbData) ? null : dbData.toString();
    }
}
