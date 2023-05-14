package com.cloudstorage.authentication.domain;

import com.cloudstorage.authentication.dto.LoginRequest;

public interface UserSession {
    String token();

    void doRefresh();

    void doLogin(LoginRequest request);
}
