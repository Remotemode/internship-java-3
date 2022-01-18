package com.remotemode.nternship3inventoryconsumer.dto;

import com.remotemode.nternship3inventoryconsumer.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String secondName;

    public static UserDto of(User user) {
        return new UserDto(user.getFirstName(), user.getSecondName());
    }
}
