package com.tweet.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tweet.api.dtos.UserDTO;
import com.tweet.api.services.UserService;

import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("sign-up")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UUID signUp(@RequestBody @Valid UserDTO userDTO) {
        return userService.signUp(userDTO);
    }
}
