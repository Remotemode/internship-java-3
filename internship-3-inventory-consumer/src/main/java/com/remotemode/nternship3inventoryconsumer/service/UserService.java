package com.remotemode.nternship3inventoryconsumer.service;

import com.remotemode.nternship3inventoryconsumer.dto.UserDto;
import com.remotemode.nternship3inventoryconsumer.model.User;
import com.remotemode.nternship3inventoryconsumer.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService implements IUserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDto userDto) {
        return userRepository.save(new User(userDto.getFirstName(), userDto.getSecondName()));
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
