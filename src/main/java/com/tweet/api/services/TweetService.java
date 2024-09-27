package com.tweet.api.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tweet.api.dtos.TweetDTO;
import com.tweet.api.models.Tweet;
import com.tweet.api.models.User;
import com.tweet.api.repositories.TweetRepository;
import com.tweet.api.repositories.UserRepository;

@Service
public class TweetService {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public UUID createTweet(TweetDTO tweetDTO) {
        Optional<User> user = userRepository.findById(tweetDTO.userId());

        if (user.isPresent())
            return tweetRepository.save(new Tweet(tweetDTO, user.get())).getId();

        return null;
    }
}
