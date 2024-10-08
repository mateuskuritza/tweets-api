package com.tweet.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tweet.api.dtos.TweetDTO;
import com.tweet.api.models.Tweet;
import com.tweet.api.models.User;
import com.tweet.api.repositories.TweetRepository;
import com.tweet.api.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
class TweetServiceTest {
    @Autowired
    private TweetService tweetService;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clearDatabase() {
        tweetRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void createTweetIfUserExists() {
        User user = new User("John Doe", "https://example.com/avatar.jpg");
        userRepository.save(user);

        TweetDTO tweetDTO = new TweetDTO(user.getId().toString(), "Hello, World!");

        tweetService.createTweet(tweetDTO);

        assertEquals(1, tweetRepository.count());

        final Tweet tweet = tweetRepository.findAll().get(0);
        assertEquals(tweet.getUser().getId(), user.getId());
        assertEquals(tweetDTO.content(), tweet.getText());
    }

    @Test
    void shouldNotCreateTweetIfUserNotExists() {
        UUID fakeUserId = UUID.randomUUID();
        TweetDTO tweetDTO = new TweetDTO(fakeUserId.toString(), "Hello, World!");

        try {
            tweetService.createTweet(tweetDTO);
        } catch (EntityNotFoundException e) {
            assertEquals(0, tweetRepository.count());
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    void getTweetsByUserId() {
        User user = new User("John Doe", "https://example.com/avatar.jpg");
        User anotherUser = new User("John Doe", "https://example.com/avatar.jpg");
        userRepository.save(user);
        userRepository.save(anotherUser);

        TweetDTO firstTweetFromUser = new TweetDTO(user.getId().toString(), "Hello, World!");
        TweetDTO secondTweetFromUser = new TweetDTO(user.getId().toString(), "Hello, World!");
        TweetDTO tweetFromAnotherUser = new TweetDTO(anotherUser.getId().toString(), "Hello, World!");
        tweetService.createTweet(firstTweetFromUser);
        tweetService.createTweet(secondTweetFromUser);
        tweetService.createTweet(tweetFromAnotherUser);

        assertEquals(3, tweetRepository.count());
        assertEquals(2, tweetService.getTweetsByUserId(user.getId()).size());
    }

    @Test
    void getTweetsByUserIdShouldReturnEmptyArrayIfUserDoesntExists() {
        User user = new User("John Doe", "https://example.com/avatar.jpg");
        userRepository.save(user);

        TweetDTO tweet = new TweetDTO(user.getId().toString(), "Hello, World!");
        tweetService.createTweet(tweet);

        assertEquals(1, tweetRepository.count());
        assertEquals(0, tweetService.getTweetsByUserId(UUID.randomUUID()).size());
    }

    @Test
    void getTweets() {
        User user = new User("John Doe", "https://example.com/avatar.jpg");
        userRepository.save(user);

        final int totalTweets = 11;
        for (int i = 0; i < totalTweets; i++) {
            TweetDTO tweetDTO = new TweetDTO(user.getId().toString(), "Hello, World!");
            tweetService.createTweet(tweetDTO);
        }

        final int pageSize = totalTweets - 1;
        Page<TweetDTO> tweets = tweetService.getTweets(Pageable.ofSize(pageSize));

        assertEquals(totalTweets, tweetRepository.count());
        assertEquals(pageSize, tweets.getSize());
        assertEquals(totalTweets, tweets.getTotalElements());
        assertEquals(2, tweets.getTotalPages());
    }
}
