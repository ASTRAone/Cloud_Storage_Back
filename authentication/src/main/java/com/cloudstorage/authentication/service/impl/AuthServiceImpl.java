package com.cloudstorage.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import com.cloudstorage.authentication.domain.UserSession;
import com.cloudstorage.authentication.dto.AuthResponse;
import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.dto.LoginResponse;
import com.cloudstorage.authentication.dto.SignupRequest;
import com.cloudstorage.authentication.dto.UserCreateRequest;
import com.cloudstorage.authentication.service.AuthService;
import com.cloudstorage.authentication.service.SessionManager;
import com.cloudstorage.authentication.service.AuthUserService;
import com.cloudstorage.common.processing.api.ProcessingResult;
import com.cloudstorage.common.processing.impl.SimpleImmutableProcessingResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SessionManager sessionManager;
    private final AuthUserService userService;

    @Override
    public AuthResponse login(LoginRequest request) {
        UserSession userSession = sessionManager.login(request);
        return new AuthResponse(userSession.token(), new LoginResponse());
    }

    @Override
    public ProcessingResult<AuthResponse> signup(SignupRequest request) {
        String keycloakUuid = userService.create(prepareUserCreateRequest(request));
        AuthResponse authResponse = login(new LoginRequest(request.getEmail(), request.getPassword()));
        return SimpleImmutableProcessingResult.ofSuccess(authResponse);
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
