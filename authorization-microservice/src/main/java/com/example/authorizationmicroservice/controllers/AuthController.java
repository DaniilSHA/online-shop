package com.example.authorizationmicroservice.controllers;

import com.example.authorizationmicroservice.dto.TokenDto;
import com.example.authorizationmicroservice.dto.UserDto;
import com.example.authorizationmicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserDto userDto) {
        return new TokenDto(userService.checkUser(userDto));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserDto userDto) {
        userService.register(userDto);
    }

}
