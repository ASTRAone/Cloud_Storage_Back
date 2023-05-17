package com.cloudstorage.authentication.service.impl;

import com.cloudstorage.authentication.domain.RefreshableUserSession;
import com.cloudstorage.authentication.domain.UserSession;
import com.cloudstorage.authentication.domain.UserSessionData;
import com.cloudstorage.authentication.service.KeycloakService;
import com.cloudstorage.authentication.service.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class InMemorySessionRepository implements SessionRepository {
    private final KeycloakService keycloakService;
    private final Map<String, UserSessionData> db = new ConcurrentHashMap<>();

    @Override
    public UserSession initNew() {
        return new RefreshableUserSession(keycloakService);
    }

    @Override
    public Optional<UserSession> find(String token) {
        return Optional.ofNullable(db.get(token))
                .map(userSessionData -> new RefreshableUserSession(keycloakService, userSessionData));
    }

    @Override
    public void store(UserSession userSession) {
        db.put(userSession.token(), userSession.data());
    }

    @Override
    public void update(String oldToken, UserSession userSession) {
        db.remove(oldToken);
        db.put(userSession.token(), userSession.data());
    }
}
