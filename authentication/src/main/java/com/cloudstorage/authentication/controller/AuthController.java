package com.cloudstorage.authentication.controller;

import lombok.RequiredArgsConstructor;
import com.cloudstorage.authentication.dto.AuthResponse;
import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.dto.LoginResponse;
import com.cloudstorage.authentication.dto.SignupRequest;
import com.cloudstorage.authentication.service.AuthService;
import com.cloudstorage.authentication.utils.TokenUtils;
import com.cloudstorage.common.processing.api.ProcessingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        return prepareLoginResponse(authResponse);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<LoginResponse> doRefresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (isEmpty(authorization)) {
            return ResponseEntity.badRequest().build();
        }

        AuthResponse authResponse = authService.refreshToken(TokenUtils.cutBearer(authorization));
        return prepareLoginResponse(authResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<ProcessingResult<LoginResponse>> signup(@RequestBody SignupRequest request) {
        ProcessingResult<AuthResponse> authResult = authService.signup(request);

        if (authResult.status().isFailed()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(null);
        }

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + authResult.content().getAccessToken())
                .body(null);
    }

    private ResponseEntity<LoginResponse> prepareLoginResponse(AuthResponse authResponse) {
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + authResponse.getAccessToken())
                .body(null);
    }
}
