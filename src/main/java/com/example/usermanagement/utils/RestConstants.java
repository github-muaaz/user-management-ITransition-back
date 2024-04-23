package com.example.usermanagement.utils;

import com.example.usermanagement.controller.auth.AuthController;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface RestConstants {
    ObjectMapper objectMapper = new ObjectMapper();

    String AUTHENTICATION_HEADER = "Authorization";

    String REFRESH_TOKEN_HEADER = "RefreshToken";

    String[] OPEN_PAGES = {
            "/*",
            AuthController.AUTH_CONTROLLER_BASE_PATH + "/**",
            RestConstants.BASE_PATH + "/**"
    };
    String BASE_PATH = "/api/v1/";
}
