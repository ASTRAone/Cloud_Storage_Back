package com.cloudstorage.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.cloudstorage.authentication.client.KeycloakClient;
import com.cloudstorage.authentication.dto.UserCreateRequest;
import com.cloudstorage.authentication.exception.UnauthorizedException;
import com.cloudstorage.authentication.service.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserServiceImpl implements AuthUserService {
    private final KeycloakClient keycloakClient;

    @Override
    public String create(UserCreateRequest request) {
        String location;
        try (Response response = keycloakClient.create(request)) {
            if (response.getStatus() != HttpStatus.CREATED.value()) {
                log.error("status: {}", response.getStatus());
                log.error(response.readEntity(String.class));
                throw new UnauthorizedException("User was not created");
            }
            location = response.getHeaderString(HttpHeaders.LOCATION);
        }
        return location.substring(location.indexOf("users/") + 6);
    }
}
