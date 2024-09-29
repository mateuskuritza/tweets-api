package com.tweet.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweet.api.dtos.TweetDTO;
import com.tweet.api.models.Tweet;
import com.tweet.api.models.User;
import com.tweet.api.repositories.TweetRepository;
import com.tweet.api.repositories.UserRepository;

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

        TweetDTO tweetDTO = new TweetDTO(user.getId(), "Hello, World!");

        final UUID tweetId = tweetService.createTweet(tweetDTO);

        assertEquals(1, tweetRepository.count());

        final Tweet tweet = tweetRepository.findById(tweetId).get();
        assertEquals(tweetDTO.userId(), user.getId());
        assertEquals(tweetDTO.content(), tweet.getText());
    }

    @Test
    void shouldNotCreateTweetIfUserNotExists() {
        UUID fakeUserId = UUID.randomUUID();
        TweetDTO tweetDTO = new TweetDTO(fakeUserId, "Hello, World!");

        final UUID tweetId = tweetService.createTweet(tweetDTO);

        assertEquals(0, tweetRepository.count());
        assertEquals(null, tweetId);
    }
}
