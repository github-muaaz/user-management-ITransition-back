package com.example.usermanagement.controller.auth;

import com.example.usermanagement.payload.api.ApiResult;
import com.example.usermanagement.payload.auth.SignInDTO;
import com.example.usermanagement.payload.auth.SignUpDTO;
import com.example.usermanagement.payload.auth.TokenDTO;
import com.example.usermanagement.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signDTO) {
        return authService.signIn(signDTO);
    }

    @Override
    public ApiResult<TokenDTO> signUp(SignUpDTO signUpDTO) {
        return authService.signUp(signUpDTO);
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        return authService.refreshToken(accessToken, refreshToken);
    }
}
