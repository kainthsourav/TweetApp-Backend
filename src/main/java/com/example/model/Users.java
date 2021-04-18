package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class Users {

    @Id
    private String emailId;
    private String loginId;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean loggedIn;

    @DBRef
    private List<Tweet> tweets;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Users() {}

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Users(String emailId, String loginId, String firstName, String lastName, String password, Boolean loggedIn, List<Tweet> tweets) {
        this.emailId = emailId;
        this.loginId = loginId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.loggedIn = loggedIn;
        this.tweets = tweets;
    }

    @Override
    public String toString() {
        return "User = [emailId=" + emailId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
                + ", tweetDate=" + ", passwordKey=" + password + "]";
    }

}
