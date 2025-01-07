package com.td.repository;

import com.td.entity.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FraudRepository extends JpaRepository<CardDetails,String> {

    Optional<CardDetails> findById(String cardNumber);
}
