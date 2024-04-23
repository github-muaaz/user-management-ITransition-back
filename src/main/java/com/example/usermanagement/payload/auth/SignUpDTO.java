package com.example.usermanagement.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    @NotBlank(message = "MUST_NOT_BE_BLANK_NAME")
    private String name;

    @NotBlank(message = "MUST_NOT_BE_BLANK_EMAIL")
    private String email;

    @NotBlank(message = "MUST_NOT_BE_BLANK_PASSWORD")
    private String password;

}
