package com.example.controller;

import com.example.config.KafkaConsumerConfig;
import com.example.model.Tweet;
import com.example.model.Users;
import com.example.repository.TweetRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TweetRepository tweetRepo;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    @PostMapping("/register")
    public ResponseEntity<?> newTweet(@RequestBody Tweet tweet){
        try {
            if (userRepo.existsByLoginId(tweet.getEmailId())){
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String formattedDate = myDateObj.format(myFormatObj);
                tweet.setDateOfTweet(formattedDate);
                kafkaProducer.sendKafkaTweetData(tweet);
                tweetRepo.save(tweet);
                return new ResponseEntity<Tweet>(tweet, HttpStatus.OK);
            }else{
                throw  new Exception(tweet.getEmailId() +" is not a registered user");
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
                throw new Exception(userName + " is not a registered user");
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get All Tweets
    @GetMapping("/tweets")
    public ResponseEntity<?> getAllTweets(){
        try {
            List<Tweet> allTweets = tweetRepo.findAll();
            return new ResponseEntity<>(allTweets, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
