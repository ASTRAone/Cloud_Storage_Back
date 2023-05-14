package com.cloudstorage.authentication.config;

import com.cloudstorage.authentication.properties.KeycloakClientProperties;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.ClientBuilderWrapper;
import org.keycloak.admin.client.JacksonProvider;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.BasicAuthFilter;
import org.keycloak.admin.client.token.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Configuration
public class KeycloakConfig {
    @Bean
    public Keycloak keycloak(KeycloakClientProperties properties) {
        return KeycloakBuilder.builder()
                .serverUrl(properties.getAuthServerUrl())
                .realm(properties.getRealm())
                .clientId(properties.getResource())
                .clientSecret(properties.getClientSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }

    @Bean
    public TokenService tokenService(KeycloakClientProperties properties, Client client) {
        WebTarget target = client.target(properties.getAuthServerUrl());
        target.register(new BasicAuthFilter(properties.getResource(), properties.getClientSecret()));
        return Keycloak.getClientProvider().targetProxy(target, TokenService.class);
    }

    @Bean
    public Client client() {
        ClientBuilder clientBuilder = ClientBuilderWrapper.create(null, false);
        clientBuilder.register(JacksonProvider.class, 100);
        return clientBuilder.build();
    }
}
