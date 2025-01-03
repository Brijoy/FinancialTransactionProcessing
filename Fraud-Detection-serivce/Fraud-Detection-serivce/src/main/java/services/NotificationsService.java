package services;

import Entity.FraudDetectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationsService {

    @Autowired
    private RestTemplate restTemplate;

    public String getNotificationDetails(FraudDetectionEntity fraudDetectionEntity) {
        String url = "http://other-microservice/api/notifications/" + fraudDetectionEntity;
        return restTemplate.getForObject(url, String.class);
    }

    public void sendNotification(String notification) {
        String url = "http://other-microservice/api/notifications";
        restTemplate.postForObject(url, notification, Void.class);
    }
}
