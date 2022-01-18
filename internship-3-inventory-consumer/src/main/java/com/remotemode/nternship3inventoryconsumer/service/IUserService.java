package com.remotemode.nternship3inventoryconsumer.service;

import com.remotemode.nternship3inventoryconsumer.dto.UserDto;
import com.remotemode.nternship3inventoryconsumer.model.User;

import java.util.Optional;

public interface IUserService {
    User createUser(UserDto userDto);

    Optional<User> findUserById(Long userId);
}
