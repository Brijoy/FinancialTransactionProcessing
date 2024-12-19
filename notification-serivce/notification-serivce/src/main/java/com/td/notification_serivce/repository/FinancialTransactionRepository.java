package com.td.notification_serivce.repository;

import com.td.notification_serivce.entity.FinancialTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransactionEntity,Long> {
}
