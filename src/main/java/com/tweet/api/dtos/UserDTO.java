package com.tweet.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
                @NotBlank @NotNull String username,
                @NotBlank @NotNull String avatarUrl) {
}
