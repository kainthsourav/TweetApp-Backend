package com.example.repository;

import com.example.model.Tweet;
import com.example.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TweetRepository extends MongoRepository<Tweet, Integer> {
    Tweet findById(int id);
    List<Tweet> findByIsOriginalTweet(boolean isOriginal);
}
