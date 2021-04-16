package com.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class Users {

    @Id
    private String emailId;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private Boolean loggedIn;

    @DBRef
    private List<Tweet> tweets;

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Users() {}

    public Users(String emailId){
        this.emailId = emailId;
    }

    public Users(String emailId, String firstName, String lastName, String gender, String birthDate,
                 String passwordKey, Boolean loggedIn) {
        super();
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = birthDate;
        this.password = passwordKey;
        this.loggedIn = loggedIn;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public String toString() {
        return "User = [emailId=" + emailId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
                + gender + ", birthDate=" + dateOfBirth + ", passwordKey=" + password + "]";
    }


}
