package com.cloudstorage.user.api;

import com.cloudstorage.common.processing.api.ProcessingResult;
import com.cloudstorage.user.api.dto.KeycloakUserDto;
import com.cloudstorage.user.api.dto.UserApi;
import com.cloudstorage.user.api.dto.UserDto;
import com.cloudstorage.user.service.crud.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClient implements UserApi {
    private final UserService userService;

    @Override
    public ProcessingResult<Void> createUser(KeycloakUserDto userDto) {
        return userService.createUser(userDto);
    }

    @Override
    public UserDto findByKeycloakUuid(String keycloakUuid) {
        return userService.findByKeycloakUuid(keycloakUuid);
    }
}
