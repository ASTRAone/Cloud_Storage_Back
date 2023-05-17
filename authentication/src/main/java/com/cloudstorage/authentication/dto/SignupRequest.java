package com.cloudstorage.authentication.dto;

import com.cloudstorage.common.domain.user.FIO;
import lombok.Data;

@Data
public class SignupRequest {
    private final String email;
    private final String password;
    private final FIO fio;
}
