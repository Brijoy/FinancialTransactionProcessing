package com.td.notification_serivce.controller;

import com.td.notification_serivce.dto.FinancialTransactionDTO;
import com.td.notification_serivce.service.FinancialTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-transactions")

public class FinancialTransactionController {
    @Autowired
    private FinancialTransactionService financialTransactionService;

    public FinancialTransactionController(FinancialTransactionService financialTransactionService) {
        this.financialTransactionService = financialTransactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialTransactionDTO> getFinancialTransactionById(@PathVariable Long id) {
        FinancialTransactionDTO financialTransactionDTO = financialTransactionService.getFinancialTransactionById(id);
        return ResponseEntity.ok(financialTransactionDTO);
    }

    @PostMapping
    public ResponseEntity<FinancialTransactionDTO> createFinancialTransaction(@RequestBody FinancialTransactionDTO financialTransactionDTO) {
        FinancialTransactionDTO createdFinancialTransactionDTO = financialTransactionService.createFinancialTransaction(financialTransactionDTO);
        return ResponseEntity.ok(createdFinancialTransactionDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FinancialTransactionDTO> updateFinancialTransaction(@PathVariable Long id, @RequestBody FinancialTransactionDTO financialTransactionDTO) {
        FinancialTransactionDTO updatedFinancialTransactionDTO = financialTransactionService.updateFinancialTransaction(financialTransactionDTO);
        return ResponseEntity.ok(updatedFinancialTransactionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinancialTransaction(@PathVariable Long id) {
        financialTransactionService.deleteFinancialTransaction(id);
        return ResponseEntity.noContent().build();
    }


}
