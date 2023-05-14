package com.cloudstorage.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import com.cloudstorage.authentication.domain.UserSession;
import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.exception.UnauthorizedException;
import com.cloudstorage.authentication.service.SessionManager;
import com.cloudstorage.authentication.service.SessionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionManagerImpl implements SessionManager {
    private final SessionRepository sessionRepository;

    @Override
    public UserSession login(LoginRequest request) {
        UserSession userSession = sessionRepository.initNew();
        userSession.doLogin(request);
        sessionRepository.store(userSession);
        return userSession;
    }

    @Override
    public UserSession refresh(String oldToken) {
        UserSession userSession = findSession(oldToken);
        userSession.doRefresh();
        sessionRepository.update(oldToken, userSession);
        return userSession;
    }

    private UserSession findSession(String token) {
        return sessionRepository.find(token).orElseThrow(UnauthorizedException::new);
    }
}
