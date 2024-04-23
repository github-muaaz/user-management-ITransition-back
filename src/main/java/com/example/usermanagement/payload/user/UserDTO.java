package com.example.usermanagement.payload.user;

import com.example.usermanagement.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private UUID id;

    private String name;

    private String email;

    private LocalDateTime lastLoginTime;

    private LocalDateTime registrationTime;

    private boolean status;

    public static UserDTO map(User user) {
        if (Objects.isNull(user))
            return null;

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .lastLoginTime(user.getLastLogin())
                .registrationTime(user.getRegisteredAt())
                .status(user.isEnabled())
                .build();
    }

    public static List<UserDTO> map(Page<User> users) {
        return users
                .stream()
                .map(UserDTO::map)
                .toList();
    }
}