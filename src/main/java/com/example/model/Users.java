package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class Users {

    @Id
    private String id;
    private String emailId;
    private String loginId;
    private String firstName;
    private String lastName;
    private String password;
    private String contactNo;

    @DBRef
    private List<Tweet> tweets;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
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

    public Users(String emailId, String loginId, String firstName, String lastName, String password, String contactNo, List<Tweet> tweets) {
        this.emailId = emailId;
        this.loginId = loginId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.contactNo = contactNo;
        this.tweets = tweets;
    }

    public Users(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User = [emailId=" + emailId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
                + ", tweetDate=" + ", passwordKey=" + password + "]";
    }

}
