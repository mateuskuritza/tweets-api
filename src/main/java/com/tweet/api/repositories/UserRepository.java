package com.tweet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweet.api.models.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
