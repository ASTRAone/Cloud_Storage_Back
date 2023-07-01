package com.cloudstorage.authentication.domain;

import com.cloudstorage.authentication.dto.LoginRequest;

public interface UserSession {
    String token();

    UserSessionData data();

    void doRefresh();

    void doLogin(LoginRequest request);
}
