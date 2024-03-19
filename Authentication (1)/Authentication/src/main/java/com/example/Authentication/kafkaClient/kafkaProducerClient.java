package com.example.Authentication.kafkaClient;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class kafkaProducerClient {
    private KafkaTemplate<String, String> kafkaTemplate;

    public kafkaProducerClient(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
    }
}
