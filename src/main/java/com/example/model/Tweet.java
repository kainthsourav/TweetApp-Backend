package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "tweet")
public class Tweet {

    @Id
    private String emailId;
    private String tweetMessage;
    private Date dateOfTweet;

    public Tweet(){

    }
    public Tweet(String email_id, String tweet_description, Date tweetDate) {
        this.emailId = email_id;
        this.tweetMessage = tweet_description;
        this.dateOfTweet = tweetDate;
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

    public Date getDateOfTweet() {
        return dateOfTweet;
    }

    public void setDateOfTweet(Date dateOfTweet) {
        this.dateOfTweet = dateOfTweet;
    }

}
