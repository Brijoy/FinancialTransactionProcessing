package com.td.controller;

import com.td.dto.CardDetailsDTO;
import com.td.dto.OTPValidationDTO;
import com.td.exception.FraudException;
import com.td.service.FraudService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fraud")
@AllArgsConstructor
public class FraudController {

    @Autowired
    private FraudService fraudService;

    @PostMapping("/validate-card")
    public ResponseEntity<String> validateCardDetails( @RequestBody CardDetailsDTO cardDetails) {
        boolean isValid = fraudService.validateCardDetails(cardDetails);
        if (!isValid) {
            throw new FraudException("Invalid card details.");
        }
        return ResponseEntity.ok("Card validated. OTP generated.");
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOTP(@RequestBody OTPValidationDTO otpValidation) {
        // Replace the cardNumber with a value you intend to associate with OTP in the database
        String cardNumber = otpValidation.getCardNumber(); // Should come from the frontend or session

        boolean isValid = fraudService.validateOtp(cardNumber, otpValidation.getOtp());
        if (!isValid) {
            throw new FraudException("Invalid OTP.");
        }
        return ResponseEntity.ok("OTP validated successfully.");
    }
}
