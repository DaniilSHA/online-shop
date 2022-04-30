package com.example.authorizationmicroservice.services;

import com.example.authorizationmicroservice.dto.UserDto;

public interface UserService {
    void register(UserDto userDto);

    String checkUser(UserDto userDto);
}
