package com.example.usermanagement.payload.user;

import com.example.usermanagement.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMeDTO {

    private UUID id;

    private String name;

    private String email;

    private boolean enabled;

    public static UserMeDTO map(User user) {
        if (Objects.isNull(user))
            return null;

        return UserMeDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getUsername())
                .enabled(user.isEnabled())
                .build();
    }
}