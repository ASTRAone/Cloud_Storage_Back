package com.cloudstorage.authentication.service;

import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.dto.UserCreateRequest;
import org.keycloak.representations.AccessTokenResponse;

import javax.ws.rs.core.Response;

public interface KeycloakService {
    AccessTokenResponse grantToken(LoginRequest request);

    Response create(UserCreateRequest request);

    AccessTokenResponse refresh(String refreshToken);

    Response delete(String keycloakUuid);

    String extractSubjectFromToken(String token);
}
