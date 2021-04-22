package com.example.controller;

import com.example.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public SendResult<String, String> sendKafkaTweetData(Tweet tweet) throws JsonProcessingException, ExecutionException, InterruptedException {

        String key = tweet.getEmailId();
        String value = objectMapper.writeValueAsString(tweet);
        SendResult<String, String> listenableFuture = null;

        try {
            listenableFuture = kafkaTemplate.sendDefault(key, value).get();
            System.out.println("Kafka Producer Working");
            log.info("Kafka Producer Tweet Added Successfully with key: {} and value: {}",key,value);
        } catch (ExecutionException | InterruptedException e) {
            log.error("Error in try catch: {}",e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error in try catch: {}",e.getMessage());
            throw e;
        }

        return listenableFuture;
    }

    private void handleErrors(String key, String value, Throwable ex) {
        log.error("Kafka Producer - error added tweet and the exception is: {}",ex);
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in try catch: {}",throwable.getMessage());
        }
    }

    private void handleSuccess(String key, String value, SendResult<String, String> result) {
        log.info("Kafka Producer Tweet Added Successfully with key: {} and value: {}",key,value);
    }
}
