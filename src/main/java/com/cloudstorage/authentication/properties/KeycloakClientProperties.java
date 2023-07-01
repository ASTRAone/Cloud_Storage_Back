package com.cloudstorage.authentication.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties("keycloak")
public class KeycloakClientProperties {
    private String serverUrl;
    private String realm;
    private String resource;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;
}
