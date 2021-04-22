package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collation = "tweet-replies")
public class TweetReply {

    @Id
    String loginId;
    List<Tweet> tweetReply;

    TweetReply(){}

    public TweetReply(String loginId, List<Tweet> tweetReply) {
        this.loginId = loginId;
        this.tweetReply = tweetReply;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public List<Tweet> getTweetReply() {
        return tweetReply;
    }

    public void setTweetReply(List<Tweet> tweetReply) {
        this.tweetReply = tweetReply;
    }
}
