package com.td.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FraudTransactionData {
        private String transactionId;
        private String transactionAmount;
        private String recipientEmail;

}
