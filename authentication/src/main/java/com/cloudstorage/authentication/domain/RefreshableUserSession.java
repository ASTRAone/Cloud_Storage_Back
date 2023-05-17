package com.cloudstorage.authentication.domain;

import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.service.KeycloakService;
import org.keycloak.representations.AccessTokenResponse;

public class RefreshableUserSession implements UserSession {
    private final KeycloakService keycloakService;
    private final UserSessionData data;

    public RefreshableUserSession(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
        this.data = new UserSessionData();
    }

    public RefreshableUserSession(KeycloakService keycloakService, UserSessionData data) {
        this.keycloakService = keycloakService;
        this.data = data;
    }

    @Override
    public String token() {
        return data.getCurrentToken().getToken();
    }

    public UserSessionData data() {
        return data;
    }

    @Override
    public void doRefresh() {
        String refreshToken = data.getCurrentToken().getRefreshToken();
        data.setCurrentToken(keycloakService.refresh(refreshToken));
    }

    @Override
    public void doLogin(LoginRequest request) {
        AccessTokenResponse currentToken = keycloakService.grantToken(request);
        data.setCurrentToken(currentToken);
        String keycloakUuid = keycloakService.extractSubjectFromToken(currentToken.getToken());
        data.setKeycloakUuid(keycloakUuid);
    }
}
