package com.cloudstorage.authentication.dto;

import lombok.Data;
import com.cloudstorage.common.domain.user.FIO;

@Data
public class SignupRequest {
    private final String email;
    private final String username;
    private final String password;
    private final FIO fio;
}
