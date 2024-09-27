package com.tweet.api.services;

import java.util.UUID;
import org.springframework.stereotype.Service;

import com.tweet.api.dtos.UserDTO;
import com.tweet.api.models.User;
import com.tweet.api.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID signUp(UserDTO userDTO) {
        return userRepository.save(new User(userDTO)).getId();
    }
}
