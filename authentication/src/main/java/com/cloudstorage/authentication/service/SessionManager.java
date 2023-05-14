package com.cloudstorage.authentication.service;

import com.cloudstorage.authentication.domain.UserSession;
import com.cloudstorage.authentication.dto.LoginRequest;

public interface SessionManager {
    UserSession login(LoginRequest request);

    UserSession refresh(String token);
}
