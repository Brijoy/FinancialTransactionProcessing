package com.td.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinancialTransactionDTO {
    private Long id;
    private UserDTO user;
    private String transactionType;
    private BigDecimal transactionAmount;



}
