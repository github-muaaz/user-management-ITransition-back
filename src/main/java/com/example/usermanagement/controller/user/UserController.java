package com.example.usermanagement.controller.user;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.payload.api.ApiResult;
import com.example.usermanagement.payload.user.UserDTO;
import com.example.usermanagement.payload.user.UserMeDTO;
import com.example.usermanagement.security.CurrentUser;
import com.example.usermanagement.utils.RestConstants;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(path = UserController.USER_CONTROLLER_BASE_PATH)
public interface UserController {
    String USER_CONTROLLER_BASE_PATH = RestConstants.BASE_PATH + "user";

    String USER_ME_PATH = "/me";

    String LIST = "/list/{page}/{size}";

    String UNBLOCK_USER_PATH = "/unblock";

    @GetMapping(value = USER_ME_PATH)
    ApiResult<UserMeDTO> getUserMe(@CurrentUser User user);

    @GetMapping(LIST)
    ApiResult<List<UserDTO>> getAll(@PathVariable Integer page, @PathVariable Integer size);

    @DeleteMapping
    ApiResult<?> delete(@RequestBody List<UUID> idList);

    @PutMapping
    ApiResult<?> block(@RequestBody List<UUID> idList);

    @PutMapping(UNBLOCK_USER_PATH)
    ApiResult<?> unblock(@RequestBody List<UUID> idList);
}
