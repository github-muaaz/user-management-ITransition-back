package com.example.usermanagement.service.user;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.exceptions.RestException;
import com.example.usermanagement.payload.api.ApiResult;
import com.example.usermanagement.payload.user.UserDTO;
import com.example.usermanagement.payload.user.UserMeDTO;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.utils.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ApiResult<UserMeDTO> getUserMe(User user) {
        if (Objects.isNull(user))
            throw RestException
                    .restThrow("User not found", HttpStatus.NOT_FOUND);

        UserMeDTO userDTO = UserMeDTO.map(user);

        return ApiResult.successResponse(userDTO);
    }

    @Override
    public ApiResult<List<UserDTO>> getAll(Integer page, Integer size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));

        return ApiResult.successResponse(UserDTO.map(users));
    }

    @Override
    public ApiResult<?> delete(List<UUID> idList) {

        userRepository.deleteAllById(idList);

        return ApiResult.successResponse(MessageConstants.SUCCESSFULLY_DELETED);
    }

    @Override
    public ApiResult<?> block(List<UUID> idList) {

        changeStatusOfUsers(idList, false);

        return ApiResult.successResponse(MessageConstants.SUCCESSFULLY_BLOCKED);
    }

    @Override
    public ApiResult<?> unblock(List<UUID> idList) {
        changeStatusOfUsers(idList, true);

        return ApiResult.successResponse(MessageConstants.SUCCESSFULLY_UNBLOCKED);
    }

    private void changeStatusOfUsers(List<UUID> idList, boolean enabled) {
        List<User> users = userRepository.findAllById(idList);

        users = users.stream().peek(user -> user.setEnabled(enabled)).toList();

        userRepository.saveAll(users);
    }
}
