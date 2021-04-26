package com.example.model;

import org.bson.codecs.AtomicIntegerCodec;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Document(collection = "tweet")
public class Tweet {

    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    private int id;
    private String emailId;
    private String tweetMessage;
    private boolean isOriginalTweet;
    private String dateOfTweet;
    private List<Integer> tweetReply = new ArrayList<Integer>();

    public Tweet(){
        this.id = count.incrementAndGet();
    }

    public Tweet(int id, String emailId, String tweetMessage, boolean isOriginalTweet, String dateOfTweet, List<Integer> tweetReply) {
        this.id = id;
        this.emailId = emailId;
        this.tweetMessage = tweetMessage;
        this.isOriginalTweet = isOriginalTweet;
        this.dateOfTweet = dateOfTweet;
        this.tweetReply = tweetReply;
    }

    public int getId() {
        return id;
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

    public boolean isOriginalTweet() {
        return isOriginalTweet;
    }

    public void setOriginalTweet(boolean originalTweet) {
        this.isOriginalTweet = originalTweet;
    }

    public List<Integer> getTweetReply() {
        return tweetReply;
    }

    public void setTweetReply(int tweetReply) {
        this.tweetReply.add(tweetReply);
    }
}
