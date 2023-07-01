package com.cloudstorage.user.mapper;

import com.cloudstorage.common.mapper.api.EntityMapper;
import com.cloudstorage.user.dto.UserDto;
import com.cloudstorage.user.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserEntityMapper implements EntityMapper<UserEntity, UserDto> {
    @Mapping(target = ".", source = "data")
    public abstract UserDto toDto(UserEntity userEntity);
    @Mapping(target = "data", source = ".")
    public abstract UserEntity toEntity(UserDto userDto);
}
