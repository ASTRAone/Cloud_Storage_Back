package com.cloudstorage.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakUserDto {
    private UserDto user;
    private String keycloakUuid;
}
