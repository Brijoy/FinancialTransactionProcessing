package com.td.notification_service.service;

import com.td.notification_service.dto.NotificationDTO;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO notificationDTO);
    public void deleteNotification(Long id) ;
    public NotificationDTO getNotificationById(Long id);
    public NotificationDTO updateNotification(NotificationDTO notificationDTO);
    public void sendNotification(String to, String subject, String body);
    public void sendNotificationMail(String recipientEmail, String transactionDetails);




}
