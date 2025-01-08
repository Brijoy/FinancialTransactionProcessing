package com.td.notification_service.service;

import com.td.notification_service.dto.NotificationTemplateDTO;

public interface NotificationTemplateService {
    public NotificationTemplateDTO getNotificationTemplateById(Long id);
    public NotificationTemplateDTO createNotificationTemplate(NotificationTemplateDTO notificationTemplateDTO);
    public NotificationTemplateDTO updateNotificationTemplate(NotificationTemplateDTO notificationTemplateDTO);
    public void deleteNotificationTemplate(Long id);

}
