package com.cloudstorage.authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSessionData {
    private String keycloakUuid;
    private String userUuid;
    private AccessTokenResponse currentToken;
}
