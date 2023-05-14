package com.cloudstorage.authentication.client;

import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.dto.UserCreateRequest;
import org.keycloak.representations.AccessTokenResponse;

import javax.ws.rs.core.Response;

public interface KeycloakClient {
    AccessTokenResponse grantToken(LoginRequest request);

    Response create(UserCreateRequest request);

    AccessTokenResponse refresh(String refreshToken);
}
