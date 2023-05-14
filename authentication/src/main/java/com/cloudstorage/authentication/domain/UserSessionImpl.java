package com.cloudstorage.authentication.domain;

import lombok.RequiredArgsConstructor;
import com.cloudstorage.authentication.client.KeycloakClient;
import com.cloudstorage.authentication.dto.LoginRequest;
import org.keycloak.representations.AccessTokenResponse;

@RequiredArgsConstructor
public class UserSessionImpl implements UserSession {
    private final KeycloakClient keycloakClient;
    private AccessTokenResponse currentToken;

    @Override
    public String token() {
        return currentToken.getToken();
    }

    @Override
    public void doRefresh() {
        currentToken = keycloakClient.refresh(currentToken.getRefreshToken());
    }

    @Override
    public void doLogin(LoginRequest request) {
        currentToken = keycloakClient.grantToken(request);
    }
}
