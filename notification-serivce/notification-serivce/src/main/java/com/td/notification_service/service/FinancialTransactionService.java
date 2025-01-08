package com.td.notification_service.service;

import com.td.notification_service.dto.FinancialTransactionDTO;

public interface FinancialTransactionService {

    public FinancialTransactionDTO getFinancialTransactionById(Long id);
    public FinancialTransactionDTO createFinancialTransaction(FinancialTransactionDTO financialTransactionDTO);
    public FinancialTransactionDTO updateFinancialTransaction(FinancialTransactionDTO financialTransactionDTO);
    public void deleteFinancialTransaction(Long id);
}
