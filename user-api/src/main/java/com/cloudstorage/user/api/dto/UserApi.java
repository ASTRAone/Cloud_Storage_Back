package com.cloudstorage.user.api.dto;

import com.cloudstorage.common.processing.api.ProcessingResult;

public interface UserApi {
    ProcessingResult<Void> createUser(KeycloakUserDto userDto);

    UserDto findByKeycloakUuid(String keycloakUuid);
}
