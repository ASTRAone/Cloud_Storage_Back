package com.cloudstorage.authentication.service;

import com.cloudstorage.authentication.dto.AuthResponse;
import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.dto.SignupRequest;
import com.cloudstorage.common.processing.api.ProcessingResult;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    ProcessingResult<AuthResponse> signup(SignupRequest request);

    AuthResponse refreshToken(String token);
}
