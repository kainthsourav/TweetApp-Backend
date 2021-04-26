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
import java.util.ArrayList;
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
    public ResponseEntity<?> newTweet(@RequestBody Tweet newTweet){
        try {
//            System.out.println("New Tweet Tweeted By: " + newTweet.getEmailId() );
//            System.out.println("New Tweet: " + newTweet.getTweetMessage() );
//            System.out.println("Is New Tweet Original: " + newTweet.isOriginalTweet() );
            if (userRepo.existsByLoginId(newTweet.getEmailId())){
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String formattedDate = myDateObj.format(myFormatObj);
                newTweet.setOriginalTweet(true);
                newTweet.setDateOfTweet(formattedDate);
                kafkaProducer.sendKafkaTweetData(newTweet);
                tweetRepo.save(newTweet);
                return new ResponseEntity<Tweet>(newTweet, HttpStatus.OK);
            }else{
                throw  new Exception(newTweet.getEmailId() +" is not a registered user");
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
            List<Tweet> allOriginalTweets = tweetRepo.findByIsOriginalTweet(true);
//            System.out.println("Count of original tweet is: " + allOriginalTweets.size());
//            List<Tweet> allTweets = tweetRepo.findAll();
            return new ResponseEntity<>(allOriginalTweets, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reply-register/{id}")
    public ResponseEntity<?> newReplyTweet(@PathVariable("id") int tweetId, @RequestBody Tweet newTweet){
        //System.out.println("did i reach");
        try {
            Tweet oldTweet = new Tweet();
            oldTweet = tweetRepo.findById(tweetId);
//            System.out.println("Old Tweet is: " + oldTweet.getTweetMessage() );
//            System.out.println("New Tweet is: " + newTweet.getTweetMessage() );
//            System.out.println("Old Tweet id is: " + oldTweet.getId() );
//            System.out.println("New Tweet id is: " + newTweet.getId() );
            oldTweet.setTweetReply(newTweet.getId());

            tweetRepo.save(oldTweet);

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String formattedDate = myDateObj.format(myFormatObj);
            newTweet.setDateOfTweet(formattedDate);
            newTweet.setOriginalTweet(false);
            tweetRepo.save(newTweet);
            return new ResponseEntity<Tweet>(newTweet, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Tweets for specific individual
    @GetMapping("/replytweets/{id}")
    public ResponseEntity<?> getTweetReply(@PathVariable("id") int tweetId){
        List<Tweet> allReplies = new ArrayList<Tweet>();
        List<Integer> tweetIds;
        Tweet originalTweet;
        try {
            originalTweet = tweetRepo.findById(tweetId);
            tweetIds = originalTweet.getTweetReply();
            if (tweetIds == null){
                throw new Exception("No Tweet Reply");
            }
            System.out.println("Tweet Ids: " + tweetIds);
            for (int tweet: tweetIds) {
                allReplies.add(tweetRepo.findById(tweet));
            }
            return new ResponseEntity<>(allReplies, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
