package com.cloudstorage.authentication.service.impl;

import com.cloudstorage.authentication.domain.UserSession;
import com.cloudstorage.authentication.dto.AuthResponse;
import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.dto.LoginResponse;
import com.cloudstorage.authentication.dto.SignupRequest;
import com.cloudstorage.authentication.dto.UserCreateRequest;
import com.cloudstorage.authentication.service.AuthService;
import com.cloudstorage.authentication.service.AuthUserService;
import com.cloudstorage.authentication.service.SessionManager;
import com.cloudstorage.common.processing.api.ProcessingResult;
import com.cloudstorage.common.processing.impl.SimpleImmutableProcessingResult;
import com.cloudstorage.user.api.dto.KeycloakUserDto;
import com.cloudstorage.user.api.dto.UserApi;
import com.cloudstorage.user.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SessionManager sessionManager;
    private final AuthUserService userService;
    private final UserApi userApi;

    @Override
    public AuthResponse login(LoginRequest request) {
        UserSession userSession = sessionManager.initNewSession(request);
        UserDto userDto = userApi.findByKeycloakUuid(userSession.data().getKeycloakUuid());
        userSession.data().setUserUuid(userDto.getUuid());
        sessionManager.store(userSession);
        return new AuthResponse(userSession.token(), new LoginResponse(userDto));
    }

    @Override
    public ProcessingResult<AuthResponse> signup(SignupRequest request) {
        String keycloakUuid = userService.create(prepareUserCreateRequest(request));
        try {
            KeycloakUserDto keycloakUserDto = new KeycloakUserDto(
                    UserDto.builder().fio(request.getFio()).email(request.getEmail()).build(),
                    keycloakUuid
            );
            ProcessingResult<Void> userCreateResult = userApi.createUser(keycloakUserDto);
            if (userCreateResult.status().isFailed()) {
                userService.delete(keycloakUuid);
                return SimpleImmutableProcessingResult.ofFailed(userCreateResult.errors());
            } else {
                AuthResponse authResponse = login(new LoginRequest(request.getEmail(), request.getPassword()));
                return SimpleImmutableProcessingResult.ofSuccess(authResponse);
            }
        } catch (Exception e) {
            userService.delete(keycloakUuid);
            throw e;
        }
    }

    @Override
    public AuthResponse refreshToken(String token) {
        UserSession session = sessionManager.refresh(token);
        return new AuthResponse(session.token(), null);
    }

    private UserCreateRequest prepareUserCreateRequest(SignupRequest request) {
        return UserCreateRequest.builder()
                .username(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFio().getFirstName())
                .lastName(request.getFio().getLastName())
                .build();
    }
}
