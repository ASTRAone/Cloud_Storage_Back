package com.cloudstorage.common.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class StringUtils {
    public static String encodeToURL(String input) {
        if (input == null) {
            return null;
        }
        return URLEncoder.encode(input, StandardCharsets.UTF_8);
    }
}
