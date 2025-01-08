package com.td.notification_service.service.impl;

import com.td.notification_service.dto.FinancialTransactionDTO;
import com.td.notification_service.entity.FinancialTransactionEntity;
import com.td.notification_service.mapper.FinancialTransactionMapper;
import com.td.notification_service.repository.FinancialTransactionRepository;
import com.td.notification_service.service.FinancialTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialTransactionServiceImpl implements FinancialTransactionService {

    @Autowired
    private FinancialTransactionRepository financialTransactionRepository;


    @Override
    public FinancialTransactionDTO getFinancialTransactionById(Long id) {
        FinancialTransactionEntity financialTransactionEntity = financialTransactionRepository.findById(id).orElse(null);
        return FinancialTransactionMapper.toDTO(financialTransactionEntity);

    }

    @Override
    public FinancialTransactionDTO createFinancialTransaction(FinancialTransactionDTO financialTransactionDTO) {
        FinancialTransactionEntity financialTransaction = FinancialTransactionMapper.toEntity(financialTransactionDTO);
        FinancialTransactionEntity savedFinancialTransaction = financialTransactionRepository.save(financialTransaction);
        return FinancialTransactionMapper.toDTO(savedFinancialTransaction);

    }

    @Override
    public FinancialTransactionDTO updateFinancialTransaction(FinancialTransactionDTO financialTransactionDTO) {
        FinancialTransactionEntity existingFinancialTransactionEntity = financialTransactionRepository.findById(financialTransactionDTO.getId()).orElse(null);
        if (existingFinancialTransactionEntity != null) {
            existingFinancialTransactionEntity = FinancialTransactionMapper.toEntity(financialTransactionDTO);
            FinancialTransactionEntity savedFinancialTransaction = financialTransactionRepository.save(existingFinancialTransactionEntity);
            return FinancialTransactionMapper.toDTO(savedFinancialTransaction);
        } else {
            return null;
        }

    }

    @Override
    public void deleteFinancialTransaction(Long id) {

    }
}
