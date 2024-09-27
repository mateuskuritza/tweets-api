package com.tweet.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.tweet.api.dtos.TweetDTO;

@Entity
@Table(name = "tweets")
@Data
@NoArgsConstructor
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String text;

    public Tweet(TweetDTO tweetDTO, User user) {
        this.user = user;
        this.text = tweetDTO.content();
    }
}
