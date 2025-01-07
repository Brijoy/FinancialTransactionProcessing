package controllers;

import dto.AttemptRecordsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AttemptRecordsService;

@RestController
@RequestMapping("/attempt-records")
public class AttemptRecordsController {

    @Autowired
    private AttemptRecordsService attemptRecordsService;

    @PostMapping(value = "/create")
    public void createAttemptRecord(@RequestBody AttemptRecordsDTO dto) {
        AttemptRecordsDTO attemptRecordsDTO = attemptRecordsService.getAttemptRecordById(dto.getTransactionId());
        if (attemptRecordsDTO != null) {
            attemptRecordsDTO.setAttemptsCount(dto.getAttemptsCount());
            attemptRecordsDTO.setCvvAttempts(dto.getCvvAttempts());
            attemptRecordsDTO.setOtpAttempts(dto.getOtpAttempts());
            attemptRecordsDTO.setOtp(dto.getOtp());
            attemptRecordsDTO.setStatus(dto.getStatus());
            attemptRecordsDTO.setUserId(dto.getUserId());
            attemptRecordsDTO.setTransactionId(dto.getTransactionId());
            attemptRecordsDTO.setTimestamp(dto.getTimestamp());
            attemptRecordsDTO.setId(dto.getId());
            attemptRecordsDTO.setCvv(dto.getCvv());
            attemptRecordsService.saveAttemptRecord(attemptRecordsDTO);
        } else {
            attemptRecordsService.saveAttemptRecord(dto);
        }
    }

    @GetMapping("/{id}")
    public AttemptRecordsDTO getAttemptRecord(@PathVariable Long id) {
        return attemptRecordsService.getAttemptRecordById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttemptRecord(@PathVariable Long id) {
        attemptRecordsService.deleteAttemptRecord(id);
        return ResponseEntity.noContent().build();
    }
}
