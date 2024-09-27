package com.tweet.api.dtos;

import java.util.UUID;

import com.tweet.api.models.Tweet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TweetDTO(
        @NotNull UUID userId,
        @NotBlank @NotNull String content) {

    public TweetDTO(Tweet tweet) {
        this(tweet.getUser().getId(), tweet.getText());
    }
}
