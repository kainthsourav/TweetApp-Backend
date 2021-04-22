package com.example.controller;

import com.example.model.Tweet;
import com.example.repository.TweetReplyRepository;
import com.example.repository.TweetRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("/api/v1.0/tweet")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TweetReplyController{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TweetRepository tweetRepo;

    @Autowired
    private TweetReplyRepository tweetReplyRepo;

    @PostMapping("/reply")
    public ResponseEntity<?> newTweet(@RequestBody Tweet tweet){
        try {
            if (userRepo.existsByLoginId(tweet.getEmailId())){
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String formattedDate = myDateObj.format(myFormatObj);
                tweet.setDateOfTweet(formattedDate);
//                kafkaProducer.sendKafkaTweetData(tweet);
                
                return new ResponseEntity<Tweet>(tweet, HttpStatus.OK);
            }else{
                throw  new Exception(tweet.getEmailId() +" is not a registered user");
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
