package com.example.usermanagement.controller.user;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.payload.api.ApiResult;
import com.example.usermanagement.payload.user.UserDTO;
import com.example.usermanagement.payload.user.UserMeDTO;
import com.example.usermanagement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ApiResult<UserMeDTO> getUserMe(User user) {
        return userService.getUserMe(user);
    }

    @Override
    public ApiResult<List<UserDTO>> getAll(Integer page, Integer size) {
        return userService.getAll(page, size);
    }

    @Override
    public ApiResult<?> delete(List<UUID> idList) {
        return userService.delete(idList);
    }

    @Override
    public ApiResult<?> block(List<UUID> idList) {
        return userService.block(idList);
    }

    @Override
    public ApiResult<?> unblock(List<UUID> idList) {
        return userService.unblock(idList);
    }
}
