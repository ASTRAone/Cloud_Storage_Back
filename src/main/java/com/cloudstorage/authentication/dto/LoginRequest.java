package com.cloudstorage.authentication.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private final String username;
    private final String password;
}
