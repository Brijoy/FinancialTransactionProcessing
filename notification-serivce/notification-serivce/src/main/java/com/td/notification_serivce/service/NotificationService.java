package com.td.notification_serivce.service;

import com.td.notification_serivce.dto.NotificationDTO;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO notificationDTO);
    public void deleteNotification(Long id) ;
    public NotificationDTO getNotificationById(Long id);
    public NotificationDTO updateNotification(NotificationDTO notificationDTO);
    public void sendNotification(String to, String subject, String body);




}
