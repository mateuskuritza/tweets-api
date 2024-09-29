package com.tweet.api.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tweet.api.dtos.UserDTO;
import com.tweet.api.models.User;
import com.tweet.api.repositories.UserRepository;

import java.util.UUID;

@SpringBootTest // Carrega o contexto completo do Spring
class UserServiceTest {

        @Autowired
        UserService userService;

        @Autowired
        UserRepository userRepository; // Usa o repositório real

        @Test
        void signUp() {
                final UserDTO userDTO = new UserDTO(
                                "John Doe",
                                "https://example.com/avatar.jpg");

                final UUID userId = userService.signUp(userDTO);

                assertEquals(1, userRepository.count());

                final User user = userRepository.findById(userId).get();
                assertEquals(userId, user.getId());
                assertEquals(userDTO.username(), user.getUsername());
                assertEquals(userDTO.avatarUrl(), user.getAvatarUrl());
        }
}
