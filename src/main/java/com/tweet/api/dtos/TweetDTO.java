package com.tweet.api.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TweetDTO(
        @NotNull UUID userId,
        @NotBlank @NotNull String content) {
}
