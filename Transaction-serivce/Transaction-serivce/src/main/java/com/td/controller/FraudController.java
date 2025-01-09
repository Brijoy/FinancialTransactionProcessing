package com.td.controller;

import com.td.dto.CardDetailsDTO;
import com.td.service.FraudService;
import com.td.exception.FraudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fraud")
public class FraudController {

    @Autowired
    private FraudService fraudService;

    // Card validation API that generates and sends OTP to Kafka
    @PostMapping("/validate-card")
    public boolean validateCard(@RequestBody CardDetailsDTO cardDetailsDTO) {
        return fraudService.validateCardDetails(cardDetailsDTO);
    }

    // OTP validation API that validates the OTP by consuming it from Kafka
    @PostMapping("/validate-otp")
    public boolean validateOtp(@RequestParam String cardNumber, @RequestParam String otp) {
        // Here, the OTP will be consumed from Kafka and validated
        try {
            return fraudService.validateOtp(cardNumber, otp);
        } catch (FraudException e) {
            return false;  // If OTP validation fails, return false
        }
    }
}
