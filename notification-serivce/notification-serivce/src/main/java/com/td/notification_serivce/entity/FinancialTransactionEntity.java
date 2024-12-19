package com.td.notification_serivce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "financial_transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinancialTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String transactionType;
    private BigDecimal transactionAmount;



}
