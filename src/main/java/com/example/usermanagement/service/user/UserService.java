package com.example.usermanagement.service.user;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.payload.api.ApiResult;
import com.example.usermanagement.payload.user.UserDTO;
import com.example.usermanagement.payload.user.UserMeDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    ApiResult<UserMeDTO> getUserMe(User user);

    ApiResult<List<UserDTO>> getAll(Integer page, Integer size);

    ApiResult<?> delete(List<UUID> idList);

    ApiResult<?> block(List<UUID> idList);

    ApiResult<?> unblock(List<UUID> idList);
}
