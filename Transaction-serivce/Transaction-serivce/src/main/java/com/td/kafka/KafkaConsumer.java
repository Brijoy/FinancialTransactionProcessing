package com.td.kafka;

import com.td.entity.CardDetails;
import com.td.exception.FraudException;
import com.td.repository.FraudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private FraudRepository fraudRepository;

    // Listen for OTP submission and validate it
    @KafkaListener(topics = "otp-topic", groupId = "otp-validation-group")
    public void listenForOtp(String message) {
        // The message is in the format cardNumber:otp
        String[] parts = message.split(":");
        if (parts.length != 2) {
            throw new FraudException("Invalid OTP message format.");
        }

        String cardNumber = parts[0];
        String otp = parts[1];

        CardDetails card = fraudRepository.findById(cardNumber).orElse(null);
        if (card == null) {
            throw new FraudException("Card not found.");
        }

        // Validate OTP
        if (!card.getOtp().equals(otp)) {
            throw new FraudException("Invalid OTP.");
        }

        // Optionally, you can delete OTP after validation or take other actions.
        card.setOtp(null); // OTP is used, now it's cleared
        fraudRepository.save(card);

        System.out.println("OTP validated successfully for card: " + cardNumber);
    }
}
