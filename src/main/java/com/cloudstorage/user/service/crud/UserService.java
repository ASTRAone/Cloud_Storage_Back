package com.cloudstorage.user.service.crud;

import com.cloudstorage.common.processing.api.ProcessingResult;
import com.cloudstorage.common.service.api.CrudService;
import com.cloudstorage.user.dto.KeycloakUserDto;
import com.cloudstorage.user.dto.UserDto;

public interface UserService extends CrudService<UserDto> {
    ProcessingResult<Void> createUser(KeycloakUserDto userDto);

    UserDto findByKeycloakUuid(String keycloakUuid);
}
