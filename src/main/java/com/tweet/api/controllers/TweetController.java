package com.tweet.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.tweet.api.services.TweetService;

import jakarta.persistence.EntityNotFoundException;
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
        try {
            tweetService.createTweet(tweetDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tweet created");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with id " + tweetDTO.userId() + " not found");
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("{userId}")
    public List<TweetDTO> getTweetsByUserId(@PathVariable UUID userId) {
        return tweetService.getTweetsByUserId(userId);
    }

    @GetMapping()
    public Page<TweetDTO> getTweets(@PageableDefault(page = 0, size = 5) Pageable page) {
        return tweetService.getTweets(page);
    }
}
