package com.cloudstorage.authentication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateRequest {
    private final String username;
    private final String password;
    private final String lastName;
    private final String firstName;
}
