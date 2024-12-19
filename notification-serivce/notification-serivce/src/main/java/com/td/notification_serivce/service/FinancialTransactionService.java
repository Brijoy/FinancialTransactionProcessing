package com.td.notification_serivce.service;

import com.td.notification_serivce.dto.FinancialTransactionDTO;

public interface FinancialTransactionService {

    public FinancialTransactionDTO getFinancialTransactionById(Long id);
    public FinancialTransactionDTO createFinancialTransaction(FinancialTransactionDTO financialTransactionDTO);
    public FinancialTransactionDTO updateFinancialTransaction(FinancialTransactionDTO financialTransactionDTO);
    public void deleteFinancialTransaction(Long id);
}
