package com.td.service;

import com.td.dto.CardDetailsDTO;
import com.td.entity.CardDetails;
import com.td.exception.FraudException;
import com.td.repository.FraudRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FraudService {

    private static final String CARD_REGEX = "^[0-9]{16}$"; // Validate card number as 16 digits
    private static final String CVV_REGEX = "^[0-9]{3}$";  // Validate CVV as 3 digits
    private static final String MONTH_REGEX = "^(0[1-9]|1[0-2])$"; // Expiry Month (01-12)
    private static final String YEAR_REGEX = "^[2-9][0-9]{3}$"; // Expiry Year (e.g. 2025)

    @Autowired
    private FraudRepository fraudRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;  // Kafka template for sending messages

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    // Method to validate card details and send OTP to Kafka
    public boolean validateCardDetails(CardDetailsDTO cardDetailsDTO) {
        // Validate and retrieve card details from database
        CardDetails existingCard = fraudRepository.findById(cardDetailsDTO.getCardNumber()).orElse(null);
        if (existingCard == null) {
            throw new FraudException("Card number does not exist.");
        }

        if (!existingCard.getCvv().equals(cardDetailsDTO.getCvv())) {
            throw new FraudException("Invalid CVV.");
        }

        if (!existingCard.getExpiryMonth().equals(cardDetailsDTO.getExpiryMonth())) {
            throw new FraudException("Invalid expiry month.");
        }

        if (!existingCard.getExpiryYear().equals(cardDetailsDTO.getExpiryYear())) {
            throw new FraudException("Invalid expiry year.");
        }

        // Generate OTP
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        String otp = generateOTP(key.getKey()); // Generate OTP using the key

        // Save OTP to the database
        existingCard.setOtp(otp);
        fraudRepository.save(existingCard);

        // Send OTP to Kafka
        sendOtpToKafka(otp, cardDetailsDTO.getCardNumber(), existingCard.getMobileNumber());

        return true;
    }

    // Generate OTP based on Google Authenticator secret key
    private String generateOTP(String secretKey) {
        return String.valueOf(gAuth.getTotpPassword(secretKey));
    }

    // Send OTP to Kafka topic
    private void sendOtpToKafka(String otp, String cardNumber, String mobileNumber) {
        String message = "Card Number: " + cardNumber + ", OTP: " + otp + ", Mobile: " + mobileNumber;
        kafkaTemplate.send("otp_topic", cardNumber, message);  // Sends OTP to the Kafka topic
        System.out.println("OTP sent to Kafka: " + message);
    }

    // Method to validate OTP, comparing the received OTP with the one stored in the database
    public boolean validateOtp(String cardNumber, String otp) {
        // Retrieve the card details from the database using the card number
        CardDetails card = fraudRepository.findById(cardNumber).orElse(null);
        if (card == null) {
            throw new FraudException("Card not found.");
        }

        // Validate the OTP against the stored OTP
        if (card.getOtp().equals(otp)) {
            return true;
        } else {
            throw new FraudException("Invalid OTP.");
        }
    }
}
