package com.cloudstorage.authentication.utils;

public class TokenUtils {
    public static final String AUTH_HEADER_PREFIX = "Bearer ";

    public static String cutBearer(String token) {
        return token.replace(AUTH_HEADER_PREFIX, "").trim();
    }
}
