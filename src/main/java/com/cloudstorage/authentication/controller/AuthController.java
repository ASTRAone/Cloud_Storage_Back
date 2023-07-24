package com.cloudstorage.authentication.controller;

import com.cloudstorage.authentication.dto.AuthResponse;
import com.cloudstorage.authentication.dto.LoginRequest;
import com.cloudstorage.authentication.dto.LoginResponse;
import com.cloudstorage.authentication.dto.SignupRequest;
import com.cloudstorage.authentication.dto.TokenWrapper;
import com.cloudstorage.authentication.service.AuthService;
import com.cloudstorage.authentication.utils.TokenUtils;
import com.cloudstorage.common.processing.api.ProcessingResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "auth-api", description = "Public authentication api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        return prepareLoginResponse(authResponse);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<LoginResponse> doRefresh(@RequestBody TokenWrapper tokenWrapper) {
        if (StringUtils.isEmpty(tokenWrapper.token())) {
            return ResponseEntity.badRequest().build();
        }

        AuthResponse authResponse = authService.refreshToken(TokenUtils.cutBearer(tokenWrapper.token()));
        return prepareLoginResponse(authResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signup(@RequestBody SignupRequest request) {
        ProcessingResult<AuthResponse> authResult = authService.signup(request);

        if (authResult.status().isFailed()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(null);
        }

        return prepareLoginResponse(authResult.content());
    }

    private ResponseEntity<LoginResponse> prepareLoginResponse(AuthResponse authResponse) {
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + authResponse.getAccessToken())
                .body(authResponse.getLoginResponse());
    }
}
