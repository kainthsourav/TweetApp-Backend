package com.example.controller;

import com.example.model.Tweet;
import com.example.repository.TweetRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TweetRepository tweetRepo;

    @PostMapping("/register")
    public ResponseEntity<?> newTweet(@RequestBody Tweet tweet){
        try {
            if (userRepo.existsById(tweet.getEmailId())){
                tweet.setDateOfTweet(new Date(System.currentTimeMillis()));
                tweetRepo.save(tweet);
                return new ResponseEntity<Tweet>(tweet, HttpStatus.OK);
            }else{
                throw  new Exception(tweet.getEmailId() +" does not exist in the database");
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Tweets for specific individual
    @GetMapping("/tweets/{id}")
    public ResponseEntity<?> getSpecificTweets(@PathVariable("id") String userName){
        try {
            if (userRepo.existsById(userName)){
                return new ResponseEntity<>(tweetRepo.findAll(), HttpStatus.OK);
            }else{
                throw new Exception(userName + " Does not exists in the database");
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get All Tweets
    @GetMapping("/tweets")
    public ResponseEntity<?> getAllTweets(){
        try {
            return new ResponseEntity<>(tweetRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
