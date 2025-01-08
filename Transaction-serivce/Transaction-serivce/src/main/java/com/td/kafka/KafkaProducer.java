package com.td.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Send OTP to Kafka topic
    public void sendOtp(String cardNumber, String otp) {
        // You can create a more complex message like a JSON object if needed.
        String message = cardNumber + ":" + otp;
        kafkaTemplate.send("otp-topic", message); // Sending to a Kafka topic
    }
}