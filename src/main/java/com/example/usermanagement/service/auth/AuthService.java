package com.example.usermanagement.service.auth;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.payload.api.ApiResult;
import com.example.usermanagement.payload.auth.SignInDTO;
import com.example.usermanagement.payload.auth.SignUpDTO;
import com.example.usermanagement.payload.auth.TokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.UUID;

public interface AuthService extends UserDetailsService {
    Optional<User> getUserById(UUID id);

    ApiResult<TokenDTO> signIn(SignInDTO signDTO);

    ApiResult<TokenDTO> signUp(SignUpDTO signUpDTO);

    ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken);

}
