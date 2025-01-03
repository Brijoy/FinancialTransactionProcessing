package services;

import Entity.FraudDetectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FraudDetectionRepository;

@Service
public class FraudDetectionService {

        public static String errorMessage= "Fraudulent activity detected: Too many attempts";

        @Autowired
        private FraudDetectionRepository fraudDetectRepository;

        @Autowired
        NotificationsService notificationsService;

        public FraudDetectionEntity saveFraudAttempt(FraudDetectionEntity fraudAttempt) {
            return fraudDetectRepository.save(fraudAttempt);
        }

        public void validateFraudAttempt(FraudDetectionEntity fraudDetect) throws Exception {
            if (fraudDetect.getAttempt() > 3) {
                // Call notification microservice
                sendNotification(errorMessage);
                throw new Exception(errorMessage);
            }
        }

        private void sendNotification(String message) {
            // Logic to call notification microservice API
            notificationsService.sendNotification(message);
        }
    }









