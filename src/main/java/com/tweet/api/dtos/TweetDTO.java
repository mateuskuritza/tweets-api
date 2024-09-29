package com.tweet.api.dtos;

import com.tweet.api.models.Tweet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TweetDTO(
        @NotBlank @NotNull
        // uuid
        @Pattern(message = "userId must be valid UUID", regexp = "^[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$") String userId,
        @NotBlank @NotNull String content) {

    public TweetDTO(Tweet tweet) {
        this(tweet.getUser().getId().toString(), tweet.getText());
    }
}
