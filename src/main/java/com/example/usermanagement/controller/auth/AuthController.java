package com.example.usermanagement.controller.auth;

import com.example.usermanagement.payload.api.ApiResult;
import com.example.usermanagement.payload.auth.SignInDTO;
import com.example.usermanagement.payload.auth.SignUpDTO;
import com.example.usermanagement.payload.auth.TokenDTO;
import com.example.usermanagement.utils.RestConstants;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = AuthController.AUTH_CONTROLLER_BASE_PATH)
public interface AuthController {

    String AUTH_CONTROLLER_BASE_PATH = RestConstants.BASE_PATH + "auth";
    String SIGN_IN_PATH = "/sign-in";
    String SIGN_UP_PATH = "/sign-up";
    String REFRESH_TOKEN_PATH = "/refresh-token";

    @PostMapping(value = SIGN_IN_PATH)
    ApiResult<TokenDTO> signIn(@Valid @RequestBody SignInDTO signInDTO);

    @PostMapping(value = SIGN_UP_PATH)
    ApiResult<TokenDTO> signUp(@Valid @RequestBody SignUpDTO signUpDTO);

    @GetMapping(value = REFRESH_TOKEN_PATH)
    ApiResult<TokenDTO> refreshToken(@RequestHeader(value = RestConstants.AUTHENTICATION_HEADER) String accessToken,
                                     @RequestHeader(value = RestConstants.REFRESH_TOKEN_HEADER) String refreshToken);
}
