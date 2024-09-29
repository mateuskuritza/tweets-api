package com.tweet.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tweet.api.dtos.TweetDTO;
import com.tweet.api.models.Tweet;
import com.tweet.api.repositories.TweetRepository;
import com.tweet.api.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TweetService {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public void createTweet(TweetDTO tweetDTO) {
        userRepository.findById(UUID.fromString(tweetDTO.userId()))
                .ifPresentOrElse(user -> tweetRepository.save(new Tweet(tweetDTO, user)), () -> {
                    throw new EntityNotFoundException();
                });
    }

    public List<TweetDTO> getTweetsByUserId(UUID userId) {
        return tweetRepository.findByUserId(userId).stream().map(TweetDTO::new).toList();
    }

    public Page<TweetDTO> getTweets(Pageable page) {
        return tweetRepository.findAll(page).map(TweetDTO::new);
    }
}
