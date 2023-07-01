package com.cloudstorage.authentication.service.impl;

import com.cloudstorage.authentication.domain.UserSession;
import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.exception.UnauthorizedException;
import com.cloudstorage.authentication.service.SessionManager;
import com.cloudstorage.authentication.service.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionManagerImpl implements SessionManager {
    private final SessionRepository sessionRepository;

    @Override
    public UserSession initNewSession(LoginRequest request) {
        UserSession userSession = sessionRepository.initNew();
        userSession.doLogin(request);
        return userSession;
    }

    @Override
    public UserSession refresh(String oldToken) {
        UserSession userSession = findSession(oldToken);
        userSession.doRefresh();
        sessionRepository.update(oldToken, userSession);
        return userSession;
    }

    @Override
    public void store(UserSession userSession) {
        sessionRepository.store(userSession);
    }

    private UserSession findSession(String token) {
        return sessionRepository.find(token).orElseThrow(UnauthorizedException::new);
    }
}
