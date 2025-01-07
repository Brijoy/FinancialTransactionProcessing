package com.td.service;

import com.td.dto.CardDetailsDTO;
import com.td.entity.CardDetails;
import com.td.exception.FraudException;
import com.td.repository.FraudRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

@Service
public class FraudService {

    private static final String CARD_REGEX = "^[0-9]{16}$"; // Validate card number as 16 digits
    private static final String CVV_REGEX = "^[0-9]{3}$";  // Validate CVV as 3 digits
    private static final String MONTH_REGEX = "^(0[1-9]|1[0-2])$"; // Expiry Month (01-12)
    private static final String YEAR_REGEX = "^[2-9][0-9]{3}$"; // Expiry Year (e.g. 2025)

    @Autowired
    private FraudRepository fraudRepository;

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    // Validate card details against the CardDetails table
    public boolean validateCardDetails(CardDetailsDTO cardDetailsDTO) {

        System.out.println(cardDetailsDTO.getCardNumber());

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


        GoogleAuthenticatorKey key = gAuth.createCredentials();
        String otp = generateOTP(key.getKey()); // Generate OTP using the key


        existingCard.setOtp(otp);

        fraudRepository.save(existingCard);


        sendOtpToMobile(existingCard.getMobileNumber(), otp);

        return true;
    }


    private void sendOtpToMobile(String mobileNumber, String otp) {
        // In a real-world application, you can integrate an SMS service to send the OTP to the user's mobile number.
        System.out.println("Sending OTP " + otp + " to mobile number: " + mobileNumber);
    }


    private String generateOTP(String secretKey) {
        return String.valueOf(gAuth.getTotpPassword(secretKey));
    }


    public boolean validateOtp(String cardNumber, String otp) {
        // Retrieve the card details from the database using the card number
        CardDetails card = fraudRepository.findById(cardNumber).orElse(null);
        if (card == null) {
            throw new FraudException("Card not found.");
        }


        //boolean isOtpValid = gAuth.authorize(card.getOtp(), Integer.parseInt(otp));
        System.out.println(card.getOtp());
        System.out.println(otp);

//        if (isOtpValid)
       if(card.getOtp().equals(otp)){

            return true;
        } else {

            throw new FraudException("Invalid OTP.1");
        }
    }
}

