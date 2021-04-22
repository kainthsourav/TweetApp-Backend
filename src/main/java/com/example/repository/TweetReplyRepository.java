package com.example.repository;

import com.example.model.Tweet;
import com.example.model.TweetReply;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TweetReplyRepository extends MongoRepository<TweetReply, String> {


}
