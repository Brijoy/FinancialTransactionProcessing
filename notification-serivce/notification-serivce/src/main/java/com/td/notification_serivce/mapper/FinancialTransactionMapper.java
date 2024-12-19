package com.td.notification_serivce.mapper;

import com.td.notification_serivce.dto.FinancialTransactionDTO;
import com.td.notification_serivce.entity.FinancialTransactionEntity;


//@Mapper
public class FinancialTransactionMapper {

    public static FinancialTransactionDTO toDTO(FinancialTransactionEntity financialTransaction) {
        FinancialTransactionDTO dto = new FinancialTransactionDTO();
        dto.setId(financialTransaction.getId());
        dto.setUser(UserMapper.toDTO(financialTransaction.getUser()));
        dto.setTransactionType(financialTransaction.getTransactionType());
        dto.setTransactionAmount(financialTransaction.getTransactionAmount());
        return dto;
    }

    public static FinancialTransactionEntity toEntity(FinancialTransactionDTO dto) {
        FinancialTransactionEntity entity = new FinancialTransactionEntity();
        entity.setId(dto.getId());
        entity.setUser(UserMapper.toEntity(dto.getUser()));
        entity.setTransactionType(dto.getTransactionType());
        entity.setTransactionAmount(dto.getTransactionAmount());
        return entity;
    }

   /* FinancialTransactionMapper Instance = Mappers.getMapper(FinancialTransactionMapper.class);

    //@Mapping(source = "numberOfSeats", target = "seatCount")
    FinancialTransactionDTO toDTO(FinancialTransactionEntity financialTransactionEntity);*/

}
