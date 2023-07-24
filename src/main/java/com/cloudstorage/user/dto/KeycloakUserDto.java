package com.cloudstorage.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "user with keycloack uuid")
public class KeycloakUserDto {
    private UserDto user;
    private String keycloakUuid;
}
