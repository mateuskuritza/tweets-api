package com.tweet.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweet.api.models.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, UUID> {
    public List<Tweet> findByUserId(UUID userId);
}
