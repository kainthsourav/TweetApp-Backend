package com.example.repository;

import com.example.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

public interface TweetRepository extends MongoRepository<Tweet, String> {
}
