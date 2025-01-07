package controllers;

import Entity.FraudDetectionEntity;
import dto.AttemptRecordsDTO;
import exception.FraudDetectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.AttemptRecordsService;
import services.FraudDetectionService;


@RestController
@RequestMapping("api/fraud-detection")
public class FraudDetectionController {
    private static Logger LOG = LoggerFactory.getLogger(FraudDetectionController.class);

    @Autowired
    FraudDetectionService fraudDetectionService;

    @Autowired
    AttemptRecordsService attemptRecordsService;

    @PostMapping(value = "/v1/fraud-check")
    public String checkFraud(@RequestBody FraudDetectionEntity fraudAttempt, Long transactionId) throws Exception {
        AttemptRecordsDTO attemptRecords = attemptRecordsService.getAttemptRecordById(transactionId);
        LOG.info("Enter in fraud check method");
        if (attemptRecords.getAttemptsCount() > 3) {
            createFraudAttempt(fraudAttempt);
            throw new FraudDetectionException("Fraud Detected");
        } else {
            return "Check Passed, Fraud check completed";
        }
    }

    public void createFraudAttempt(FraudDetectionEntity fraudAttempt) throws Exception {
        fraudDetectionService.validateFraudAttempt(fraudAttempt);
        fraudDetectionService.saveFraudAttempt(fraudAttempt);
    }
}