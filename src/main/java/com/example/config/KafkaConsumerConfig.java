package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConsumerConfig {

    @KafkaListener(topics = {"kafka-tweets"})
    public void onMessage(ConsumerRecord<String, String> consumerRecord){
        log.info("Consumer message: {}", consumerRecord);
    }

}
