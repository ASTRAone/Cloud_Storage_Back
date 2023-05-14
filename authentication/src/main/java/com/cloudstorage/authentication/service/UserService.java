package com.cloudstorage.authentication.service;

import com.cloudstorage.authentication.dto.UserCreateRequest;

public interface UserService {
    String create(UserCreateRequest request);
}
