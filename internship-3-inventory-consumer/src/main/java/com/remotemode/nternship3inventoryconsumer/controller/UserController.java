package com.remotemode.nternship3inventoryconsumer.controller;

import com.remotemode.nternship3inventoryconsumer.dto.UserDto;
import com.remotemode.nternship3inventoryconsumer.model.User;
import com.remotemode.nternship3inventoryconsumer.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        return new ResponseEntity<>(UserDto.of(user), HttpStatus.CREATED);
    }
}
