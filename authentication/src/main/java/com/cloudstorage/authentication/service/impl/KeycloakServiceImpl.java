package com.cloudstorage.authentication.service.impl;

import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.dto.UserCreateRequest;
import com.cloudstorage.authentication.properties.KeycloakClientProperties;
import com.cloudstorage.authentication.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.token.TokenService;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {
    private final KeycloakClientProperties properties;
    private final TokenService tokenService;
    private final JwtDecoder jwtDecoder;
    private final Keycloak keycloak;

    @Override
    public AccessTokenResponse grantToken(LoginRequest request) {
        Form loginForm = new Form()
                .param(OAuth2Constants.GRANT_TYPE, OAuth2Constants.PASSWORD)
                .param(OAuth2Constants.USERNAME, request.getUsername())
                .param(OAuth2Constants.PASSWORD, request.getPassword());
        return tokenService.grantToken(properties.getRealm(), loginForm.asMap());
    }

    @Override
    public Response create(UserCreateRequest request) {
        return keycloak.realm(properties.getRealm())
                .users()
                .create(prearepUserRepresentation(request));
    }

    @Override
    public AccessTokenResponse refresh(String refreshToken) {
        Form form = new Form().param(OAuth2Constants.GRANT_TYPE, OAuth2Constants.REFRESH_TOKEN)
                .param(OAuth2Constants.REFRESH_TOKEN, refreshToken);
        return tokenService.refreshToken(properties.getRealm(), form.asMap());
    }

    @Override
    public Response delete(String keycloakUuid) {
        return keycloak.realm(properties.getRealm())
                .users()
                .delete(keycloakUuid);
    }

    @Override
    public String extractSubjectFromToken(String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    private UserRepresentation prearepUserRepresentation(UserCreateRequest request) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(request.getUsername());
        userRepresentation.setCredentials(List.of(preparePasswordRepresentation(request.getPassword())));
        userRepresentation.setLastName(request.getLastName());
        userRepresentation.setFirstName(request.getFirstName());
        userRepresentation.setEnabled(true);

        return userRepresentation;
    }

    private CredentialRepresentation preparePasswordRepresentation(String password) {
        CredentialRepresentation cR = new CredentialRepresentation();
        cR.setTemporary(false);
        cR.setType(CredentialRepresentation.PASSWORD);
        cR.setValue(password);
        return cR;
    }
}
