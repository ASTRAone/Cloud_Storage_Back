package com.cloudstorage.authentication.service;

import com.cloudstorage.authentication.domain.UserSession;
import com.cloudstorage.authentication.dto.LoginRequest;

public interface SessionManager {
    UserSession initNewSession(LoginRequest request);

    UserSession refresh(String token);

    void store(UserSession userSession);
}
