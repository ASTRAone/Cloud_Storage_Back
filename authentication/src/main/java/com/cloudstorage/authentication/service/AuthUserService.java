package com.cloudstorage.authentication.service;

import com.cloudstorage.authentication.dto.UserCreateRequest;

public interface AuthUserService {
    String create(UserCreateRequest request);
}
