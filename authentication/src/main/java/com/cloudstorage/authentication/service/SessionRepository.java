package com.cloudstorage.authentication.service;

import com.cloudstorage.authentication.domain.UserSession;

import java.util.Optional;

public interface SessionRepository {
    UserSession initNew();

    Optional<UserSession> find(String token);

    void store(UserSession userSession);

    void update(String oldToken, UserSession userSession);
}
