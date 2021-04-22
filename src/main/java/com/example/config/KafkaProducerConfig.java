package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Bean
    public NewTopic kafkaTweets(){
        return TopicBuilder.name("kafka-tweets").partitions(1).replicas(1).build();
    }
}
