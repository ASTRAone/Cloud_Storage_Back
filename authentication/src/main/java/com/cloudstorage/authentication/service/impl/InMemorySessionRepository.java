package com.cloudstorage.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import com.cloudstorage.authentication.client.KeycloakClient;
import com.cloudstorage.authentication.domain.UserSession;
import com.cloudstorage.authentication.domain.UserSessionImpl;
import com.cloudstorage.authentication.service.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InMemorySessionRepository implements SessionRepository {
    private final KeycloakClient keycloakClient;
    private final Map<String, UserSession> db = new HashMap<>();

    @Override
    public UserSession initNew() {
        return new UserSessionImpl(keycloakClient);
    }

    @Override
    public Optional<UserSession> find(String token) {
        return Optional.ofNullable(db.get(token));
    }

    @Override
    public void store(UserSession userSession) {
        db.put(userSession.token(), userSession);
    }

    @Override
    public void update(String oldToken, UserSession userSession) {
        db.remove(oldToken);
        db.put(userSession.token(), userSession);
    }
}
