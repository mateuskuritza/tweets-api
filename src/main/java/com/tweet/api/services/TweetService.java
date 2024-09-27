package com.tweet.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<TweetDTO> getTweetsByUserId(UUID userId) {
        return tweetRepository.findByUserId(userId).stream().map(TweetDTO::new).toList();
    }

    public Page<TweetDTO> getTweets(int page) {
        int pageSize = 5;
        return tweetRepository.findAll(PageRequest.of(page, pageSize)).map(TweetDTO::new);
    }
}
