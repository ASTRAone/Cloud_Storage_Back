package com.cloudstorage.authentication.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "auth.keycloak")
public class AuthKeycloakProperties {
    private String resourceId;
    private String principalAttribute;
}