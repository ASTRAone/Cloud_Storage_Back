package com.cloudstorage.common.mapper.api;

public interface EntityMapper<ENTITY, DTO> {
    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);
}
