package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "tweet")
public class Tweet {

    @Id
    private String id;
    private String emailId;
    private String tweetMessage;
    private String dateOfTweet;

    public Tweet(){}

    public Tweet(String id, String emailId, String tweetMessage, String dateOfTweet) {
        this.id = id;
        this.emailId = emailId;
        this.tweetMessage = tweetMessage;
        this.dateOfTweet = dateOfTweet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTweetMessage() {
        return tweetMessage;
    }

    public void setTweetMessage(String tweetMessage) {
        this.tweetMessage = tweetMessage;
    }

    public String getDateOfTweet() {
        return dateOfTweet;
    }

    public void setDateOfTweet(String dateOfTweet) {
        this.dateOfTweet = dateOfTweet;
    }

}
