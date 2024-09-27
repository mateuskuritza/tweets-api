package com.tweet.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tweet.api.dtos.TweetDTO;
import com.tweet.api.models.Tweet;
import com.tweet.api.services.TweetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tweets")
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> createTweet(@RequestBody @Valid TweetDTO tweetDTO) {
        UUID tweetId = tweetService.createTweet(tweetDTO);

        if (tweetId != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Tweet created with id " + tweetId);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with id " + tweetDTO.userId() + " not found");
    }

    @GetMapping("{userId}")
    public List<TweetDTO> getTweetsByUserId(@PathVariable UUID userId) {
        return tweetService.getTweetsByUserId(userId);
    }
}
