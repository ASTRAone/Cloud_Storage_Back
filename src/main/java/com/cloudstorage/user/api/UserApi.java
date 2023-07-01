package com.cloudstorage.user.api;

import com.cloudstorage.common.processing.api.ProcessingResult;
import com.cloudstorage.user.dto.KeycloakUserDto;
import com.cloudstorage.user.dto.UserDto;

public interface UserApi {
    ProcessingResult<Void> createUser(KeycloakUserDto userDto);

    UserDto findByKeycloakUuid(String keycloakUuid);
}
